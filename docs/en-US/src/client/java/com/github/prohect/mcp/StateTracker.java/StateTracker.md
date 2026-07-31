# StateTracker (src/client/java/com/github/prohect/mcp/StateTracker.java)

## Syntax

```java
public final class com.github.prohect.mcp.StateTracker
```

## Static Initializer

_None._

## Remarks

Tracks the last state snapshot sent to the MCP caller and assembles the response envelope in a two-phase pattern (`begin` → `finish`). Drives the entire MCP state-delivery pipeline:

**Phase 1 — `begin(full)`**: Snapshots the current game state via `GameStateCollector.collect()`, diffs against the previous snapshot, and builds the beginning of the JSON envelope: `{"client_tick":N, "state":{...}}`. State members are:
- **Full mode** (`full=true` for `/state` or world change): every member is included.
- **Diff mode** (`full=false` for all other tools): only changed members are included. Members that disappeared (e.g., container closed) serialize as `null`.

`held_keys` is an exception — it is **force-included** in every envelope while non-empty, because screen transitions re-apply held boolean aliases behind the scenes and the caller must always know what is currently held.

Container and hotbar are diffed at slot granularity: full view on `/state` / open / menu identity change, afterwards only changed slots plus `empty_inv`/`container_grid` or `hotbar_empty` when they changed.

**Phase 2 — `finish(begun)`**: Drains all message channels via `GameChannels.drain()` and appends them to the envelope: `"chat":[...], "mod":[...], "sound":[...], "recipe":[...]`. Each message is delivered exactly once; empty channels are omitted. Closes the JSON object.

The `reset()` method forgets the baseline (called on world join/disconnect) so the next envelope is forced to full mode.

## See Also

| Item | Description |
|------|-------------|
| [begin](begin.md) | Phase 1: snapshot and diff |
| [finish](finish.md) | Phase 2: drain channels and close |
| [reset](reset.md) | Forget baseline on world join |
| [GameStateCollector.collect](GameStateCollector.java/collect.md) | Raw state snapshot |
| [GameChannels.drain](GameChannels.java/drain.md) | Channel message drain |
