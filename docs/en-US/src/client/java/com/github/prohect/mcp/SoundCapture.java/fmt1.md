# fmt1 method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
private static String fmt1(double v)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `v` | `double` | The value to format |

## Return value

The value formatted to 1 decimal place using `Locale.ROOT`.

## Remarks

Distance formatting helper (e.g., `"4.2"`). Uses the same pattern as `GameStateCollector.fmt1` but is a private copy in this class to avoid cross-class coupling for sound events.

## See Also

| Item | Description |
|------|-------------|
| [directionOf](directionOf.md) | The caller |
| [GameStateCollector.fmt1](GameStateCollector.java/fmt1.md) | The equivalent in GameStateCollector |
