# MCP API Rewrite — Status / Revival Context (2026-07-29)

## What was done (this session)

Full rewrite of the MCP tool API + the mod's HTTP API on branch `26.1.2_26.2` (no cross-branch sync requested).

**Mod (`src/client/java/com/github/prohect/`):**
- `mcp/GameChannels.java` (new, replaces `ChatCapture.java` DELETED) — 4 message channels: `chat` (ChatComponentMixin),
  `mod` (Log4j appender), `sound` (SoundCapture, key-coalescing `xN`), `recipe` (ClientPacketListenerMixin). Drain once per message.
- `mcp/SoundCapture.java` (new) — SoundEventListener (same hook as vanilla SubtitleOverlay), audibility filter (dist <= range),
  format `"<subtitle> [<compass>[ up|down] <dist>m]"`. Registered on JOIN (SoundManager null during onInitializeClient — that crash was fixed).
- `mcp/GameStateCollector.java` (new) — full snapshot: world_name, dimension, screen (simple name), pos{x,y,z,yaw,pitch,**feet**},
  health, absorption, hunger, saturation, armor, xp{level,percent}, effects, target{kind,name,distance,reach}, players (locator-bar),
  held_keys (builtin boolean aliases → user-facing names), held_item(+count), selected_hotbar_slot, durability, hotbar[9],
  container (enriched items: name/durability/enchanted/tooltip-if-nontrivial, empty_inv, **container_grid bug FIXED**:
  groups wrap only real cells, blank cells are plain padding, every row ends with \n).
- `mcp/StateTracker.java` (new) — envelope `{"tick":N,"state":{...},"chat":[...],...}`; getState=full, others=diff
  (null = field disappeared, omitted = unchanged/empty); **held_keys force-included in every envelope while non-empty**;
  world change forces full.
- `mcp/RecipeBookHelper.java` (new) — unlocked recipes (name/item/craftable), query matching, diff bookkeeping.
- `mcp/McpHttpServer.java` (rewritten) — endpoints /state /screenshot /runAlias /defineAlias /readCFG /writeCFG /listRecipes
  (**/logDiff REMOVED**), port fallback 25575..25584. runAlias: state BEFORE, channels AFTER immediate chain part.
- `mixin/client/ClientPacketListenerMixin.java` (new, registered in mixins.json) — recipe unlock toasts → recipe channel.
- `alias/builtinAlias/ApplyRecipeAlias.java` (new, `applyRecipe\query`, registered in BindAliasPlusClient) — places craftable
  unlocked recipe into open RecipeBookMenu grid via ServerboundPlaceRecipePacket; errors → local game chat.
- `BindAliasPlusClient.java` — JOIN: GameChannels.init/resetAll, StateTracker.reset, RecipeBookHelper.reset, SoundCapture.register.

**Bridge:** `MCP/mcp_server.js` rewritten and copied to `F:/source/BindAliasPlus-MCP/mcp_server.js` (live). Tools:
getState, getScreenshot, runAlias(+delay), defineAlias, readCFG, writeCFG, **listRecipes** (getLogDiff tool REMOVED).
`--port N` arg supported (default 25575). node --check passed.

**Zed settings** (`C:\Users\76288\AppData\Roaming\Zed\settings.json`, backup `settings.json.bak-20260729`):
getLogDiff → listRecipes everywhere (tool_permissions allow; agent-mc-w + agent-mc true; write-, write-gh, write false).

**cfg** (`run/config/bind-alias-plus.cfg`): added `applyRecipeTest_15` (+into allTests chain), `/runAlias +freeCursor`,
`/runAlias wait\10 esc` at the end (dismiss auto-pause after join).

**README.md** — MCP section: port 25575 (+fallback), /listRecipes, envelope doc, applyRecipe row.

**Backup:** `run/saves/Test_26_2` → `run/saves_backup/Test_26_2_20260729` (pre-run, 23M).

**Old game instance (F:\Games\Minecraft, held 25575) was shut down via builtinShutdown with user's OK.**

## Current state

- Test client RUNNING (runTestClient → quickPlaySingleplayer Test_26_2, player Player721, +freeCursor held via cfg
  autoload, auto-pause dismissed via cfg `wait\10 esc` — screen null verified). MCP on 127.0.0.1:25575.
  Join marker in `run/logs/latest.log`: `[10:40:24] ... autoload start`.
- Verified against the new build (via old bridge): getState full envelope ✓, `feet` rename ✓, `hotbar:[]` +
  `hotbar_empty:"1-9"` ✓, sound `[T+292] Footsteps [here 0.0m] x5` (tick prefix ✓, audibility filter ✓ — 250m trial
  spawner spam gone, key-coalescing ✓) ✓, chat/mod channel split ✓, target{kind,name,distance,reach} ✓,
  held_keys ✓, world_name ✓, minimal diff on no-op runAlias (`{"tick":N}` only) ✓.
- Zed settings + both mcp_server.js copies updated; Zed reload is being armed NOW (the revival message points here).

## Round 2 (post-benchmark feedback, 2026-07-29 ~13:10)

**Benchmark ran** (sub-agent, ~100 min): crafting loop proven in real play (applyRecipe applied planks/table/stick/wooden
pickaxe; both error paths hit). Run died to a zombie; user verdicts: (1) death-screen trap is BY DESIGN (no click alias);
(2) gametime-in-envelope NOT wanted (agents can defineAlias a sendCommand query); (3) sound direction upgraded to 3D.

**Changes implemented + verified in-game:**
- Sound/players direction → relative yaw/pitch (to view at play time), 20° clamp: `"[tick:456] Cow moos [yaw-80 pitch+0 14.7m]"`;
  `here <dist>m` at point-blank. Sound prefix `[T+N]` → `[tick:N]` (matches envelope term).
- `target`: `reach` member REMOVED → `{kind,name,distance}`.
- `container`: SLOT-LEVEL diff — full on getState/open/menu-change, afterwards only changed slots
  (`{"index":K,"item":null}` = became empty); empty_inv/container_grid re-send only when changed. Verified: swap 1↔9
  produced exactly `[{index:9 torch},{index:1 item:null}]` + empty_inv, grid omitted.
- runAlias param `delay` → RENAMED **`nap`** ("take a nap": seconds to keep waiting so deferred chain effects land in
  the response). User to confirm the name (alternatives: snooze/sleep).
- World restored from backup after benchmark (tick rate 20 + death pollution removed); save backup remains
  `run/saves_backup/Test_26_2_20260729`.

**Gotchas learned:**
- `sendCommand"time set day"` WITHOUT the backslash silently does NOTHING (unknown alias — SILENT FAILURES rule).
  Always `sendCommand\"..."`. This account's 401 profile-key error is normal for dev accounts and does NOT block commands.
- sendCommand server feedback is ASYNC (next response's chat channel, not the same one).
- Terminal became PowerShell 5.1 after reload: use `;` not `&&`; Start-Process for detached, not `rm -rf`/`cp -r` into existing dir.
- Benchmark agent RAISED tick rate to 20 mid-run (violating the setup) — next benchmark should forbid changing it.
- zed-reload wait: use `--wait 60` minimum (25s was too short, thread barely survived).

## Next steps (post-revival)

1. Verify NEW tools quickly: getState (full) → runAlias `log\revivalCheck` (expect mod channel echo) → runAlias again
   (expect minimal diff) → listRecipes (expect error "no recipe book screen open") → runAlias `+openInventory` →
   listRecipes (expect recipes list incl. diff) → runAlias `applyRecipe\torch` (expect chat error not-craftable/not-unlocked
   or success) → runAlias `-openInventory`. Also getScreenshot once (image + envelope).
2. Spawn ONE sub-agent (fresh context; it inherits profile agent-mc-w) for the benchmark. Prompt essentials:
   - Use ONLY the game tools (getState, getScreenshot, runAlias, defineAlias, readCFG, writeCFG, listRecipes).
     Do NOT read project files or use terminal/search — everything needed is in tool responses.
   - First: runAlias `sendCommand"time set day" sendCommand"tick rate 4"` — then record T0 via runAlias
     `sendCommand"time query gametime"` (answer arrives in the chat channel).
   - Goal: beat the game (defeat the Ender Dragon) WITHOUT CHEATING — no gamemode/tp/give/kill/enchant/xp/recipe/effect
     commands, no item spawning. Survival only. (`tick rate`, `time set`, `time query` are the only allowed commands.)
   - Stop when (gametime now − T0) > 48000 (2 game days; at rate 4 ≈ 3.3 real hours), then report: full current state
     (pos, inventory, progression), achievements, and DETAILED feedback on the MCP API: useful/missing/confusing info,
     errors hit, suggestions.
   - Tips for it: +x holds until -x (watch held_keys); wait\N = N game ticks (5× real time at rate 4); day = 24000 ticks.
3. When it returns: check full game log since the join marker (`run/logs/latest.log`), then summarize to the user:
   benchmark feedback + log findings + any fixes needed.
4. zed-reload log: `cat "$(dirname "$(command -v zed-reload)")/zed-reload.log" | tail`.

## Notes

- The `run/run-test-client.bat` launcher exists for `cmd //c start \"\" //min F:/source/BindAliasPlus/run/run-test-client.bat`.
- Console log: `run/test-client-console.log`; game log: `run/logs/latest.log`.
- Bridge serves whatever game holds 25575 — if the test client is dead, check no stray instance holds the port.
