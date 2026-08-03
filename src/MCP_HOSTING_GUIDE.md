# BindAlias MCP Hosting Guide

How to use BindAlias with the MCP bridge to host a Minecraft game for AI agents.

*The MCP bridge lives at [`src/mcp_server.js`](mcp_server.js). Paste
[`src/agent_system_prompt.md`](agent_system_prompt.md) into your agent's system
prompt.*

---

## Quick start

1. **Build** the mod — `./gradlew build --no-daemon` produces the JAR in `build/libs/`.
2. **Install** the JAR in `mods/` and launch Minecraft with Fabric.
3. **Join** a (dedicated, preferably) world — singleplayer, LAN open, or a server.
4. **Start** the MCP bridge — `node src/mcp_server.js` (runs in a terminal, reads stdin).
5. **Point** your MCP client at the bridge — see [Connecting your MCP client](#connecting-your-mcp-client).

The game must be running *and in a world* before the MCP tools work — the HTTP server inside the mod
only starts once you join a world.

---

## MCP architecture

```
┌───────────┐   MCP JSON-RPC (stdin/stdout)    ┌───────────────┐   HTTP (127.0.0.1:25575)       ┌───────────┐
│  MCP Host │  ◄────────────────────────────► │ mcp_server.js │  ◄───────────────────────────►│ BindAlias │
│ (Zed,etc) │         tool calls, state        │   (bridge)    │    GET /state, POST /runAlias  │  (mod)    │
└───────────┘                                  └───────────────┘                                └───────────┘
```

- **`mcp_server.js`** translates MCP `tools/call` requests into HTTP calls against the mod's
  built-in server. It's a thin bridge — all tool descriptions live in the script, the alias catalog
  and gameplay semantics live in `agent_system_prompt.md`.
- **The mod's HTTP server** runs inside the game process at `127.0.0.1:25575` (auto-falls back to
  the next free port up to `+9` if occupied; the actual port is logged on join).
- **Port override**: pass `--port N` or `--port=N` to `mcp_server.js` to match a non-default game
  port.

---

## Setting up a world for agents

### Choose the right world type

| World type     | Pros                                                       | Cons                                              |
| -------------- | ---------------------------------------------------------- | ------------------------------------------------- |
| Singleplayer   | Pause support, full control, no latency                    | Limited to one agent, port collision with host     |
| LAN open       | Friends can join, cheat toggle, gamerule control            | Same port collision risk; host must stay nearby    |
| Dedicated server | True headless, survives host disconnect, multiple agents | Needs a separate server JAR, no pause             |

**Recommendation:** singleplayer for solo botting, LAN open for observation, dedicated server for
long-running autonomous sessions.

### Game rules to set

Run these once in chat (or via `sendCommand` in a startup alias):

```
/gamerule doImmediateRespawn true     # Immediately respawn on death(agent can't respawn with aliases from the mod)
/gamerule keepInventory true          # avoid frustration on death
etc.
```

Tweak to taste — these just reduce noise for the agent.

### Agent-friendly starting state

- **Daytime** `/time set day` — lighting affects screenshots.
- **Survival** or **Creative** — the agent works in both, but survival is the real test.
- **A safe spawn** — a flat area or a simple shelter avoids immediate death.
- **Give basic gear** in creative: `sword`, `pickaxe`, `axe`, `shovel`, some `torches`, `food`,
  `crafting_table`, `furnace`, `bed`.

---

## The cfg file: agent configuration

The cfg file at `run/config/bind-alias.cfg` (test client) or `config/bind-alias.cfg` (regular
client) is the agent's *capability catalog*. Aliases and variables defined there are loaded on join
and visible via `readCFG`. Write new aliases at runtime with `defineAlias`, or batch them via
`writeCFG`.

### Minimal agent cfg

```cfg
# --- variables ---
/var offHand 41
var homeSlot 1

# --- movement helpers ---
alias snap90  yaw\90
alias snap180 yaw\180

# --- interaction helpers ---
alias punch +attack -attack
alias useBlock +use -use
alias pickup +sneak wait\1 -sneak

# --- inventory ---
alias hotbarHome slot\homeSlot
```

Variables like `homeSlot` and `offHand` let the agent keep reference points. Small helpers like
`punch` and `useBlock` save tokens and are less error-prone than raw `+attack -attack` chains.

### Reloading cfg at runtime

Use `writeCFG` (MCP tool) or `/reloadCFG` (chat command) to push new config while the game runs.
`writeCFG` reloads immediately after writing — the agent can iterate on its own aliases this way.

To clear old entries first:
```
runAlias unloadCFGAll
```

---

## Connecting your MCP client

### Zed

Edit `~/.agents/Zed/settings.json` to add a server entry under `mcpServers`:

```json
{
  "mcpServers": {
    "bindalias": {
      "command": "node",
      "args": ["F:/workspace/BindAlias/src/mcp_server.js"]
    }
  }
}
```

Adjust the path to your local checkout. Use forward slashes even on Windows (Zed translates them).

**Port override** (if the game chose a non-default port):
```json
"args": ["F:/workspace/BindAlias/src/mcp_server.js", "--port=25576"]
```

Zed spawns MCP servers only at startup. After editing `mcp_server.js` or its path, reload Zed:

```bash
zed-reload --settle 15 --wait 30 "[zed-reload] MCP bridge updated."
```

- `--wait 30` — final summary must finish within this window before Zed closes.
- `--settle 15` — time after the new window appears before injecting the revival message.

### Other MCP clients (Claude Desktop, VS Code, custom)

Any MCP client that speaks JSON-RPC over stdio works. Start the bridge as a child process:

```
node src/mcp_server.js
```

It reads tool call requests on stdin and writes responses on stdout. Stderr carries log messages
prefixed with `[mcp_server]`.

---

## Launching the game for agents

### One-shot launch (detached, survives the caller)

```bash
cmd //c start \"\" '.\gradlew.bat' runTestClient --no-daemon
```

This launches a test client that auto-joins a world (`Test_26_2` on the 26.x branch). The game
takes ~35 seconds to be ready.

### Before launching again

**Only one game client can occupy the default MCP port (25575).** Shut down the previous one
first — use the `builtinShutdown` alias via the MCP tools:

```
runAlias builtinShutdown
```

Or close the game window manually.

### Checking readiness

After launch, poll until the bridge responds:

```
getFullState
```

If it returns a valid state object (not a connection error), the game is ready for agent commands.

---

## Agent memory: readNotes / writeNotes

The mod provides a persistent key-value store (`readNotes` / `writeNotes` MCP tools) in an "agent
directory" — files in the game's `agent/` folder. The agent's session sticker lives at `sticker.md`.

### Using the sticker

The agent should:
1. **Read** `sticker.md` first — it's the agent's long-term memory of what it was doing.
2. **Update** `sticker.md` when asked to stop or when finishing a significant milestone.

The sticker pattern works across Zed reloads, game restarts, and even different agent sessions
as long as the same world persists.

### Other notes files

Any filename (no path separators, no `..`) works. Use them for:
- `todo.md` — current task list
- `inventory.md` — cached inventory state
- `world_map.md` — notable coordinates and landmarks
- `crafting_plan.md` — multi-step crafting goals

The agent reads/writes these autonomously — the host doesn't need to manage them.

---

## Scenario patterns

### Scenario 1: "Play autonomously for 30 minutes"

1. Launch the game, ensure it's in a safe spot.
2. Paste `src/agent_system_prompt.md` into the agent's system prompt.
3. Give the agent a goal: *"Explore the world, gather wood, stone, and iron, and build a small
   house. Write your progress to sticker.md periodically. Do not take naps longer than 2 ticks
   unless nothing is happening."*
4. Let the agent run. Check `sticker.md` via `readNotes` to see progress without disrupting it.

### Scenario 2: "I want to watch the agent and intervene"

1. Launch the game in LAN open mode.
2. Join from your main client as a spectator or second player.
3. Run the MCP bridge — the agent controls the host player, you observe.
4. Intervene via in-game chat (the agent sees chat messages in `state.chat`) or by typing commands.

### Scenario 3: "Recipe discovery and crafting"

1. Open the player inventory (`toggleInventory`) — this opens the recipe book.
2. Call `listRecipes` without queries to get all known recipes.
3. Query specific items: `listRecipes` with `queries: ["torch", "iron pickaxe"]`.
4. Use `applyRecipe\query` to fill the crafting grid when a crafting table or inventory is open.
5. The `craftable` field on each recipe tells the agent whether ingredients are in inventory.

### Scenario 4: "Teach the agent a new skill via writeCFG"

```
Tool: writeCFG
Args: { "content": "runAlias unloadCFGAll\nalias farmTree punch wait\20 punch\nalias gotoBed TPS cyclePerspective +use -use FPS" }
```

Then the agent can `runAlias farmTree` or `runAlias gotoBed` immediately — no game restart needed.

---

## Troubleshooting

| Symptom                                      | Likely cause                                      | Fix                                                             |
| -------------------------------------------- | ------------------------------------------------- | --------------------------------------------------------------- |
| `Cannot connect to mod` from bridge           | Game not in a world, or port mismatch             | Join a world; check the port logged on join; pass `--port=`     |
| Bridge starts but tools return errors         | Game crashed or closed                            | Restart the game client                                         |
| `getScreenshot` returns no image              | Mod not installed or version mismatch             | Check `mods/` folder; verify branch matches game version        |
| Agent calls fail silently (`runAlias`)        | Alias name typo or invalid args                   | Check the alias exists via `readCFG`; quote multi-word args     |
| Port conflict on game start                   | Another instance running                          | Shut down other instance via `builtinShutdown` or close window  |
| MCP bridge dies after Zed reload              | `zed-reload` kills child processes                | Re-add the MCP server to `settings.json`; reload Zed again      |
| `listRecipes` returns empty `recipes`         | Recipe book screen not open                       | Call `toggleInventory` or open a crafting table first           |
| `applyRecipe` logs error to chat but no throw | Crafting grid not on screen, or recipe not found  | Ensure a crafting table / inventory with crafting grid is open |

### Debug flow

1. **Check the game is running:** `getFullState` should return a state object, not an error.
2. **Inspect the cfg:** `readCFG` shows what aliases and vars are currently loaded.
3. **Read the sticker:** `readNotes sticker.md` to understand the agent's last state.
4. **Look at chat/mod channels:** every `getFullState` / `runAlias` envelope carries
   `chat` and `mod` arrays — drained exactly once per message.

---

## Performance notes

- **`getScreenshot` is in-memory** — no file I/O, no chat spam. Safe to call frequently.
- **`nap` blocks the agent** — the game keeps running but the agent can't react. Keep naps short
  (~2 ticks) unless the agent is genuinely waiting for a long cooldown.
- **State diffs are free** — every `runAlias`/`defineAlias`/`getScreenshot` response carries a
  state diff. Prefer reading these over polling `getFullState`.
- **`readNotes`/`writeNotes` are plain file I/O** — fast enough for session metadata, but don't
  use them as a polling loop.

---

## Security considerations

- The HTTP server binds to `127.0.0.1` only — no remote access by default.
- The MCP bridge is a local process — it never exposes the game to the network.
- Agents with `runAlias` can execute `/sendCommand` — they have full chat/command access. Only
  grant MCP access to trusted agents.
- On servers with anti-cheat, rapid or unusual action patterns may trigger flags. Test first on a
  private server.

---

## See also

- [`src/mcp_server.js`](mcp_server.js) — the MCP bridge source
- [`src/agent_system_prompt.md`](agent_system_prompt.md) — paste this into your agent's system prompt
- [`src/raw api instruction.json`](raw%20api%20instruction.json) — exact `tools/list` output preview
- [`build_test_sync_release_README.md`](../build_test_sync_release_README.md) — build, test, and release workflows
- [BindAlias MCP tool (GitHub)](https://github.com/Prohect/BindAlias-mcp) — companion MCP tool repo
