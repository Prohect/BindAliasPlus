# capture method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
private static void capture(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `text` | `java.lang.String` | The plain-text content of the chat message, obtained from `Text.getString()`. |

## Remarks

A private static bridge method that forwards captured message text to `ChatCapture.onSystemMessage(text)`. This indirection ensures that all capture logic is centralized in the `ChatCapture` utility class.

The method is `static` because it has no dependency on the mixin instance — it only delegates to the static `ChatCapture.onSystemMessage()`.

## See Also

| Item | Description |
|------|-------------|
| [ChatComponentMixin](ChatComponentMixin.md) | Parent mixin class |
| [captureMessage](captureMessage.md) | Caller — the `@Inject` hook on `ChatHud.addMessage(Text)` |
| [ChatCapture.onSystemMessage](../../mcp/ChatCapture.java/onSystemMessage.md) | Called by this method |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAlias/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
