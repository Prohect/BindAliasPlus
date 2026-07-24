# ChatCapture

## Fields

| Name | Type | Description |
|------|------|-------------|
| `active` | `volatile boolean` | Flag set by `begin()` / cleared by `end()`. Guards message capture. |
| `buffer` | `List<String>` | Synchronized list accumulating captured message text during an active window. |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| `begin` | `public static void ()` | Clears buffer and starts capture. |
| `end` | `public static String ()` | Stops capture, drains buffer, returns joined text. |
| `onSystemMessage` | `public static void (String)` | Called by the mixin to append a message if capture is active. |

## See Also

| Item | Description |
|------|-------------|
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | Calls `onSystemMessage()` from injected hooks |
| [McpHttpServer](../McpHttpServer.java/README.md) | Manages capture lifecycle via `begin()`/`end()` |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
