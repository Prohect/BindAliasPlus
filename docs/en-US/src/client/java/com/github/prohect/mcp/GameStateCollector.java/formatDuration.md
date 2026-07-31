# formatDuration method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String formatDuration(int ticks)
```

## Return value

Duration formatted as `"MM:SS"` (minutes and seconds from game ticks at 20 ticks/second).

## Remarks

Converts a tick count to a human-readable `MM:SS` string. Used for status effect durations and any other tick-based timer display.

## See Also

| Item | Description |
|------|-------------|
| [effectsJson](effectsJson.md) | The primary caller |
