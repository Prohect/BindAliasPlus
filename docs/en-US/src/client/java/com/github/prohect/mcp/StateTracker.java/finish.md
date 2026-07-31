# finish method (src/client/java/com/github/prohect/mcp/StateTracker.java)

## Syntax

```java
public static String finish(String begun)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `begun` | `String` | The partial envelope from `begin()` |

## Return value

The completed JSON envelope string, including channel messages: `{"client_tick":N, "state":{...}, "chat":[...], ...}`.

## Remarks

Thread-safe (calls `GameChannels.drain()` which is internally synchronized). Appends drained channel messages to the partial envelope: inserts `"chat"`, `"mod"`, `"sound"`, and `"recipe"` arrays containing new messages since the previous drain. Empty channels are omitted. Closes the JSON object with `}`.

## See Also

| Item | Description |
|------|-------------|
| [begin](begin.md) | The first phase |
| [GameChannels.drain](GameChannels.java/drain.md) | Channel message drain |
