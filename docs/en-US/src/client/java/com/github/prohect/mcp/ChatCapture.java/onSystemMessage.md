# onSystemMessage method (src/client/java/com/github/prohect/mcp/ChatCapture.java)

## Syntax

```java
public static void onSystemMessage(String text)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `text` | `String` | The plain-text message extracted from a `Component` by `ChatComponentMixin.capture()`. |

## Remarks

Entry point called by `ChatComponentMixin` whenever a system or player message is added to chat. If `active` is `true`, the message text is appended to the synchronized buffer.

This method may be called from the render thread (via Mixin injection). The `buffer.add()` call is thread-safe because `buffer` is a `Collections.synchronizedList`. The `active` check is performed without synchronization — a `volatile` read is sufficient since the flag only transitions at well-defined points (`begin`/`end` called from the HTTP thread).

## See Also

| Item | Description |
|------|-------------|
| [begin](begin.md) | Enables capture (sets active = true) |
| [ChatComponentMixin.capture](../../mixin/client/ChatComponentMixin.java/capture.md) | Caller — extracts text from Component |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
