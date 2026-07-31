# NativeImageMixin

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | Mixin has no fields — purely method injection and invoker. |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| `invokeWriteToChannel` | `abstract boolean (WritableByteChannel) throws IOException` | `@Invoker` for `NativeImage.writeToChannel`. Access-widened PNG encoder. |
| `onWriteToFile` | `private void (Path, CallbackInfo)` | `@Inject` on `NativeImage.writeToFile`. Captures PNG bytes for MCP screenshot endpoint. |

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture](../../mcp/ScreenshotCapture.java/README.md) | Destination for captured PNG bytes |
| [McpHttpServer](../../mcp/McpHttpServer.java/README.md) | Consumer in the MCP HTTP pipeline |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
