# ScreenshotCapture

## Fields

| Name | Type | Description |
|------|------|-------------|
| `nextPngFuture` | `CompletableFuture<byte[]>` | One-shot future polled by MCP HTTP handler. Set to `null` after consumption. |
| `lastPath` | `String` | Absolute filesystem path of the last captured screenshot. |
| `lastName` | `String` | File name (no directory) of the last captured screenshot. |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| _(none public)_ | | All access is via static fields; constructor is private. |

## See Also

| Item | Description |
|------|-------------|
| [NativeImageMixin](../../mixin/client/NativeImageMixin.java/README.md) | Producer — writes to these fields during screenshot save |
| [McpHttpServer](../McpHttpServer.java/README.md) | Consumer — reads `nextPngFuture` in the `/screenshot` endpoint |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
