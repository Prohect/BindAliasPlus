# capture method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
private static void capture(String text)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `text` | `String` | The plain-text message to post to the CHAT channel |

## Remarks

Private static helper called by `captureMessage`. Posts the extracted message text to [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md) via `GameChannels.post(CHAT, text)`. Does not filter or transform the text — raw `Text#getString()` output is forwarded as-is. Thread-safe because `GameChannels.post` synchronizes internally.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.post](../../../mcp/GameChannels.java/post.md) | The channel posting method |
| [ChatComponentMixin](ChatComponentMixin.md) | The enclosing mixin class |
