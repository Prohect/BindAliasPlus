# GameStateCollector (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
public final class com.github.prohect.mcp.GameStateCollector
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Utility class that assembles the raw game-state snapshot for the MCP response envelope. Called by [`StateTracker.begin`](StateTracker.java/begin.md) on every MCP request. Produces a `LinkedHashMap<String, String>` of state members as JSON fragments — keys like `world`, `pos`, `health`, `effects`, `target`, `players`, `screen`, `looking_at`, `selected_slot`, `held_keys`, `hotbar`, `container`, etc. Each value is pre-formatted as a JSON string (or null if the data is not available, such as when not in a world).

Also provides the `ContainerSnapshot` record and associated methods for container/hotbar slot-granularity diffing used by `StateTracker` to produce full views on open/menu-change and per-slot diffs thereafter. Formatting helpers (`fmt1`, `fmt2`, `jsonEscape`) are shared with other MCP classes.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | The caller that invokes `collect()` and builds the envelope |
| [collect](collect.md) | The main snapshot method |
| [containerSnapshot](containerSnapshot.md) | Extracts container menu state |
| [hotbarItems](hotbarItems.md) | Extracts hotbar slot→item mappings |
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | Reuses the same directional formatting for `players` |
