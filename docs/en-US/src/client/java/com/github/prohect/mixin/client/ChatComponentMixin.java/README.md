# ChatComponentMixin

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | Mixin has no fields — purely method injections. |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| `onAddClientSystemMessage` | `private void (Component, CallbackInfo)` | `@Inject` on `ChatComponent.addClientSystemMessage`. Captures client-side system messages. |
| `onAddServerSystemMessage` | `private void (Component, CallbackInfo)` | `@Inject` on `ChatComponent.addServerSystemMessage`. Captures server system messages. |
| `onAddPlayerMessage` | `private void (Component, MessageSignature, GuiMessageTag, CallbackInfo)` | `@Inject` on `ChatComponent.addPlayerMessage`. Captures player chat messages. |
| `capture` | `private static void (String)` | Forwards captured text to `ChatCapture.onSystemMessage()`. |

## See Also

| Item | Description |
|------|-------------|
| [ChatCapture](../../mcp/ChatCapture.java/README.md) | Target of the `capture()` dispatch |
| [McpHttpServer](../../mcp/McpHttpServer.java/README.md) | Reads captured messages from `ChatCapture` |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
