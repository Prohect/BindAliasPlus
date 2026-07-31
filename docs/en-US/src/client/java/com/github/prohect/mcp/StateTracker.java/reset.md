# reset method (src/client/java/com/github/prohect/mcp/StateTracker.java)

## Syntax

```java
public static synchronized void reset()
```

## Remarks

Forgets the baseline state snapshot: sets `last` to an empty map, nulls `lastContainer`, `lastHotbarItems`, `lastHotbarEmpty`, and resets `baselineJoinTick` to `Long.MIN_VALUE`. Called on world join/disconnect so the next `begin()` call is forced to full mode (all state members included) regardless of the `full` parameter.

## See Also

| Item | Description |
|------|-------------|
| [begin](begin.md) | The next call will produce a full snapshot |
