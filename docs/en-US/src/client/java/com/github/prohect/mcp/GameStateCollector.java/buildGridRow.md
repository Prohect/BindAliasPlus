# buildGridRow method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String buildGridRow(String[] cells, int width)
```

## Return value

A formatted grid row string with slot indices separated by spaces. Cells not present are represented as appropriate visual spacers.

## Remarks

Builds a textual representation of a single row in a crafting grid, used by `gridJson`. Cells that are `null` or empty are rendered as appropriate filler.

## See Also

| Item | Description |
|------|-------------|
| [gridJson](gridJson.md) | The caller |
