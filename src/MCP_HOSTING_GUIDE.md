# BindAlias MCP Hosting Guide

How to use BindAlias with the MCP bridge to host a Minecraft game for AI agents.

*The MCP bridge lives at [`src/mcp_server.js`](mcp_server.js). Paste
[`src/agent_system_prompt.md`](agent_system_prompt.md) into your agent's system
prompt.*

---

## Quick start

1. **Build** the mod — `./gradlew build --no-daemon` produces the JAR in `build/libs/`, or just download the latest release.
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
│ (Zed,etc) │         tool calls, state        │   (bridge)    │  /runAlias, /screenshot, ...   │ BindAlias │
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

### Game rules to set

```
/gamerule doImmediateRespawn true     # Immediately respawn on death(agent can't respawn with aliases from the mod)
/gamerule keepInventory true          # avoid frustration on death
etc.
```
You may set these via `sendCommand` in a startup alias, or set them in the game rules menu when creating a world(this way you can disable cheats in a world but still override some game rules).

Tweak to taste — these just reduce noise and make it easier for the agent.

---

### Agent-friendly starting state

- **Tick rate** `/tick rate 4` — slow the game so that the agent can react to its environment in time.
- this mod tweaks tick series of commands, allow to use without cheats, and tick rate expands to [0.1, 10000]ps (default tick rate is 20ps)

---

## The cfg file: agent configuration

The cfg file at `run\saves\<save>\bind-alias\agent.cfg` (regular
client) is the agent's *capability catalog*. Aliases and variables defined there are loaded on join
and visible via `readCFG`. Write new aliases at runtime with `defineAlias`, or batch them via
`writeCFG`.

You can do some init in `run/config/bind-alias.cfg`, it's inaccessible to the agent, eg. `runAlias +freeCursor builtinRunAlias\wait\10;esc` which bypass the cursor grab the the focus state guard for mining and pause the game at start so that you have time to pass tasks to your agent.

---

## Connecting your MCP client

### Zed

Edit `~/.agents/Zed/settings.json` to add a server entry under `mcpServers`:

```json
{
  "mcpServers": {
    "bindalias": {
      "command": "node",
      "args": ["<path_to>/mcp_server.js"]
    }
  }
}
```

Adjust the path to your local checkout. Use forward slashes even on Windows (Zed translates them).

**Port override** (if the game chose a non-default port):
```json
"args": ["F:/workspace/BindAlias/src/mcp_server.js", "--port=25576"]
```

---

### Other MCP clients (Claude Desktop, VS Code, custom)

Any MCP client that speaks JSON-RPC over stdio works. Start the bridge as a child process:

```
node src/mcp_server.js
```

It reads tool call requests on stdin and writes responses on stdout. Stderr carries log messages
prefixed with `[mcp_server]`.

---

## Agent memory: readNotes / writeNotes

The mod provides a persistent key-value store (`readNotes` / `writeNotes` MCP tools) in an "agent
directory" — files in the game's `saves\<save>\bind-alias\` folder. The agent's session notes lives there.

It is recommended to specify a fixed file for the entrypoint of agent notes in your project system prompts. As we won't provide list directory tool.

---

## Performance notes

- **Screenshots are in-memory** — no file I/O for the api call-response flow. The file is still saved to disk.
  Request a screenshot by including `{"deferredTick": N, "screenShot": true}` in the `snap` array.
- **`snap` blocks the agent** — the game keeps running but the agent can't react. Agent should keep snaps short
  (~2 ticks) unless the agent is genuinely waiting for a long cooldown.
- **State diffs are free** — every `runAlias`/`defineAlias`/`writeCFG`/`listRecipes` response carries a
  state diff. Agent should prefer reading these over requesting full snapshots (`verbose`).

---

## Security considerations

- The HTTP server binds to `127.0.0.1` only — no remote access by default unless your 'wall' allows those ports.
- Agents with `runAlias` can execute `/sendCommand` — they have full chat/command access. Only
  grant MCP access to trusted agents, or disable cheats.
- On servers with anti-cheat, rapid or unusual action patterns may trigger flags. Test first on a
  private server.

---

## See also

- [`src/mcp_server.js`](mcp_server.js) — the MCP bridge source
- [`src/agent_system_prompt.md`](agent_system_prompt.md) — paste this into your agent's system prompt
- [`src/raw api instruction.json`](raw%20api%20instruction.json) — exact `tools/list` output preview(generated by `src\sync_mcp_instructions.sh`)
- [`build_test_sync_release_README.md`](../build_test_sync_release_README.md) — build, test, and release workflows
- [BindAlias MCP tool (GitHub)](https://github.com/Prohect/BindAlias-mcp) — companion MCP tool repo
