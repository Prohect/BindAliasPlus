# fmt1 method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String fmt1(double v)
```

## Return value

The double value formatted to 1 decimal place (e.g., `"123.5"`). Uses `Locale.ROOT` for consistent decimal separator.

## Remarks

Shared formatting helper used for coordinates and distances. Equivalent to `String.format(Locale.ROOT, "%.1f", v)`.

## See Also

| Item | Description |
|------|-------------|
| [fmt2](fmt2.md) | 2-decimal-place variant |
