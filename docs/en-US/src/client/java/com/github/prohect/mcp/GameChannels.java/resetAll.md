# resetAll method (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static void resetAll()
```

## Remarks

Marks every channel as read by advancing each channel's `lastSent` cursor to match its current `cursor`. Additionally clears all coalescing `byKey` maps. This is called on world join so that stale title-screen noise accumulated before the player entered the world is not delivered to the MCP caller. Thread-safe.

## See Also

| Item | Description |
|------|-------------|
| [drain](drain.md) | The normal drain that returns messages |
| [StateTracker.reset](StateTracker.java/reset.md) | Also resets state tracking on world join |
