# fmt2 method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String fmt2(double v)
```

## Return value

The double value formatted to 2 decimal places (e.g., `"123.46"`). Uses `Locale.ROOT` for consistent decimal separator.

## Remarks

Shared formatting helper used for player orientation angles (yaw/pitch). Equivalent to `String.format(Locale.ROOT, "%.2f", v)`.

## See Also

| Item | Description |
|------|-------------|
| [fmt1](fmt1.md) | 1-decimal-place variant |
