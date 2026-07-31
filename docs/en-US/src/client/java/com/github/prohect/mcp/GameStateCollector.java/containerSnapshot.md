# containerSnapshot method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static ContainerSnapshot containerSnapshot(Minecraft mc, LocalPlayer p)
```

## Return value

A `ContainerSnapshot` if a container screen is open, or `null` if no container screen is active or the player is null.

## Remarks

Extracts the state of an open container menu (`AbstractContainerScreen`). The `ContainerSnapshot` record captures:

- `menuIdentity` — a hash-like identity from the container's slot count, title, and type, used by `StateTracker` to detect menu changes (which trigger full state dumps)
- `items` — a map of slot index→item description (only non-empty slots)
- `emptyInv` — compressed empty-slot ranges for the player inventory portion of the container
- `grid` — crafting grid representation (2×2 player, 3×3 crafting table, furnace/fuel progression)

Uses `appendTooltipIfValuable` for item descriptions and `gridJson` for grid formatting.

## See Also

| Item | Description |
|------|-------------|
| [containerFullJson](containerFullJson.md) | Full JSON formatter |
| [containerDiffJson](containerDiffJson.md) | Diff JSON formatter |
| [StateTracker.begin](StateTracker.java/begin.md) | The caller that diffs snapshots |
