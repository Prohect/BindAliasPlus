# Task: Optimize `src/agent_system_prompt.md` via E2E clean-context sub-agent benches

## Objective

Iterate on `src/agent_system_prompt.md` (the system prompt pasted into any agent by Zed IDE) and measurably improve it 
by running **real E2E benchmarks**: each bench is a **clean-context sub-agent** that plays Minecraft via the MCP tools, 
injected only with the candidate prompt.

There was a not optimized system prompt guide: `.\src\agent_system_prompt.md`.

## Bench environment

- **World**: `Test_26_2_no_cheats` — `gradlew runTestClientNoCheats`. keepInventory on,
  immediateRespawn on, clean spawn area.
- **Autoload cfg** (`run/config/bind-alias.cfg`, minimal): `+freeCursor`, unpause, `tick rate 1`
  (chained after init). Baseline speed 1 tps → agent has ~1 s per game tick to react.
- **Acceleration**: `nap >= 10` fast-forwards the integrated server to 20 tps for the nap, then
  restores previous tps (implemented + E2E-verified in `McpHttpServer.java`, commit `d761ffe5`).
- **Sub-agent tools (allowlist, nothing else)**: `runAlias`, `getScreenshot`,
  `defineAlias`, `readCFG`, `writeCFG`, `readNotes`, `writeNotes`, `listRecipes` (all
  envelope tools accept optional `verbose`/`nap`).
  No terminal, no file tools, no dev context. Per-save `agent.cfg` via read/writeCFG (empty).
- **Prompt injection**: candidate prompt written into project `AGENTS.md` (auto-injected into
  sub-agents). Bench message carries only the task, never the prompt.
- **Bench Endpoint**: until night falls = **~12000 server ticks**, measured via `sendCommand/"time query gametime"`
  (works cheat-free via the mod's TimeCommandMixin). (Was 2 days/48000, then 1 day/24000 —
  shortened to keep iteration wall time low.)

## The bench task (message given to every sub-agent, verbatim template)

> You are playing Minecraft in singleplayer survival (no cheats) through the tools you have.
> Work autonomously — do not stop to ask questions.
>
> Goal: progress as far as you can before night falls to beat the game. 
>
> When you stop (goal reached, night fell, or stuck), report:
> milestones reached, final inventory, deaths, and anything that confused you about the tools.

## Iteration protocol

1. Write candidate prompt into `AGENTS.md`.
2. Restore pristine save if the world drifted (backup: `run/saves_backup_pristine/` —
   re-backup required, current one predates world recreation).
3. Spawn clean sub-agent (tool allowlist above) with the bench task.
4. Record: milestones, prompt confusions, wall time, real state (by mcp tools yourself), read game log file.
5. Adjust `src/agent_system_prompt.md`, repeat. Baseline v0 = current committed version.

## Constraints

- Do **not** document every builtin alias in the prompt — some confuse more than they help.
- Do **not** edit the MCP bridge instructions (`src/mcp_server.js`); the bridge carries wire
  protocol only. If it seems wrong, pause and propose instead.
- Minimal changes; keep the bridge/tool-schema separation of concerns.
