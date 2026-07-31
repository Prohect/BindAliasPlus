# hotbarDiffJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String hotbarDiffJson(Map<String, String> last, Map<String, String> cur)
```

## Return value

Diff JSON object string containing only changed hotbar slots. An item with value `null` means the slot became empty. Returns `null` when nothing changed.

## Remarks

Computes a per-slot diff between current and previous hotbar item maps. Same logic as `containerDiffJson`: added/changed slots emit the new value, removed slots emit `null`.

## See Also

| Item | Description |
|------|-------------|
| [hotbarFullJson](hotbarFullJson.md) | The full variant |
| [containerDiffJson](containerDiffJson.md) | The analogous container diff |
| [StateTracker.begin](StateTracker.java/begin.md) | The caller |
