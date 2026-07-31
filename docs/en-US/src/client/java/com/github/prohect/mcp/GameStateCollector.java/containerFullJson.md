# containerFullJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String containerFullJson(ContainerSnapshot snap)
```

## Return value

Full JSON object string for the container, including `items` (all non-empty slots), `empty_inv` (compressed empty ranges), and optional `grid`.

## Remarks

Formats a `ContainerSnapshot` as a complete JSON object. Used by `StateTracker.begin` on the first snapshot, on world changes, and when the container menu identity changes. Unlike `containerDiffJson`, includes every non-empty slot.

## See Also

| Item | Description |
|------|-------------|
| [containerDiffJson](containerDiffJson.md) | The incremental diff variant |
| [StateTracker.begin](StateTracker.java/begin.md) | The caller |
