# drain method (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static Map<String, List<String>> drain()
```

## Return value

A `LinkedHashMap<String, List<String>>` in insertion order (chat, mod, sound, recipe). Each entry maps a channel name to its new messages since the previous drain. Returns an empty map when no channel has new messages.

## Remarks

Thread-safe (synchronized on an internal lock). For each channel, compares the global cursor against the `lastSent` snapshot from the previous drain. Messages with cursor > `lastSent` are collected, and `lastSent` is advanced to the current cursor. Zero-cost when no new messages arrived (all cursors equal their `lastSent`). The only caller is [`StateTracker.finish`](StateTracker.java/finish.md), which drains channels into the MCP response envelope after every tool call.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker.finish](StateTracker.java/finish.md) | The only caller |
| [resetAll](resetAll.md) | Marks all channels as read without returning messages |
| [post](post.md) | Standard message post |
| [postCoalescing](postCoalescing.md) | Coalescing message post |
