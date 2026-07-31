# post method (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static void post(String channel, String message)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `channel` | `String` | Channel name: one of `CHAT`, `MOD`, `SOUND`, or `RECIPE` |
| `message` | `String` | The message text to post; `null` and empty strings are silently dropped |

## Remarks

Posts a message to a channel as a new, independent entry. Thread-safe (synchronized on an internal lock). When the channel's buffer exceeds `MAX_BUFFER` (100), the oldest entry is evicted. This is the posting mode for `CHAT`, `MOD`, and `RECIPE` channels where each event is independent.

## See Also

| Item | Description |
|------|-------------|
| [postCoalescing](postCoalescing.md) | Coalescing variant used for the `SOUND` channel |
| [drain](drain.md) | Retrieves new messages since the previous drain |
| [CHAT](CHAT.md) | Channel constant |
| [MOD](MOD.md) | Channel constant |
| [RECIPE](RECIPE.md) | Channel constant |
