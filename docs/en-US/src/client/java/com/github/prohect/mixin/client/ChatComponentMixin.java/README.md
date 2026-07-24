# ChatComponentMixin

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| `captureMessage` | `private void (Text, CallbackInfo)` | `@Inject` hook on `ChatHud.addMessage(Text)` — the single unified entry point for all chat messages in Yarn mappings. |
| `capture` | `private static void (String)` | Private bridge that forwards the plain-text message to `ChatCapture.onSystemMessage()`. |

## See Also

| Item | Description |
|------|-------------|
| [ChatComponentMixin](ChatComponentMixin.md) | Class-level documentation with Yarn mapping notes |
| [captureMessage](captureMessage.md) | Injects into `ChatHud.addMessage(Text)` |
| [capture](capture.md) | Bridges to `ChatCapture.onSystemMessage(text)` |
| [ChatCapture](../../mcp/ChatCapture.java/README.md) | Receives captured messages for command-feedback collection |
| [ChatCapture.onSystemMessage](../../mcp/ChatCapture.java/onSystemMessage.md) | Called by `capture()` to append text to the capture buffer |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
