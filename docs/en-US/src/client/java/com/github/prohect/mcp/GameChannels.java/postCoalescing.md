# postCoalescing method (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static void postCoalescing(String channel, String key, String message)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `channel` | `String` | Channel name; intended for `SOUND` |
| `key` | `String` | Coalescing key (sound name); entries with the same undrained key are updated in place |
| `message` | `String` | The message text; if updating an existing entry, the text is replaced and an `" xN"` counter appended for N > 1 |

## Remarks

Posts a message with coalescing behavior. Two cases:

1. **New key**: if no undrained entry with the given `key` exists, appends a new entry just like `post()`.
2. **Existing key**: if an undrained entry with the same `key` exists, updates its `text` and increments its `count` in place — the entry's position in the insertion order is preserved. When `count` > 1, the message is suffixed with `" xN"`.

This allows spammy repeating sounds (footsteps, ambient crackles) to collapse into one updating line per sound type, even when interleaved with other sounds. The coalescing is scoped to undrained messages only — once `drain()` flushes messages, old keys are forgotten and new posts start fresh.

## See Also

| Item | Description |
|------|-------------|
| [post](post.md) | Non-coalescing variant |
| [SOUND](SOUND.md) | The channel this is designed for |
| [drain](drain.md) | Flushes messages and resets coalescing state |
| [SoundCapture.onPlaySound](SoundCapture.java/onPlaySound.md) | The caller that posts sound events |
