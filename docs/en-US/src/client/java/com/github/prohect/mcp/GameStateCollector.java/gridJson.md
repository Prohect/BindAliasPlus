# gridJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String gridJson(List<int[]> grid)
```

## Return value

JSON string representing the crafting grid layout, including slot indices and dimensions. Returns `null` when the grid list is empty or null.

## Remarks

Extracted from the container menu's crafting slots and the recipe book component. Each grid entry is an `int[]` of slot indices comprising one row. Formats as JSON with `rows` and `cols` metadata plus an array of row patterns (slot indices separated by spaces, e.g., `"0 1"`, `"3 4"` for a 2×2 grid).

## See Also

| Item | Description |
|------|-------------|
| [buildGridRow](buildGridRow.md) | Row formatting helper |
| [containerSnapshot](containerSnapshot.md) | The caller |
