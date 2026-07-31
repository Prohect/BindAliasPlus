# hotbarFullJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String hotbarFullJson(Map<String, String> items)
```

## Return value

Full JSON object string for hotbar slots 1-9, each keyed by the slot number. Empty slots produce `null` values.

## Remarks

Formats the hotbar item map as a complete JSON object with all 9 slots. Used by `StateTracker.begin` on the first snapshot and world changes.

## See Also

| Item | Description |
|------|-------------|
| [hotbarDiffJson](hotbarDiffJson.md) | The incremental diff variant |
| [StateTracker.begin](StateTracker.java/begin.md) | The caller |
