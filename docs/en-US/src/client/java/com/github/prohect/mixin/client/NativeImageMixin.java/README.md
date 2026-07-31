# NativeImageMixin

Mixin targeting `com.mojang.blaze3d.platform.NativeImage`. Intercepts screenshot PNG writes to capture bytes in memory for the MCP screenshot endpoint, using an access-widened invoker to the private PNG encoder.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [invokeWriteToChannel](invokeWriteToChannel.md) | `abstract boolean invokeWriteToChannel(WritableByteChannel channel)` | `@Invoker` for the private `writeToChannel` — access-widened bridge to the STB PNG encoder |
| [onWriteToFile](onWriteToFile.md) | `void onWriteToFile(Path file, CallbackInfo ci)` | `@Inject` at `HEAD` of `writeToFile` — captures screenshot PNG bytes and completes the in-progress `ScreenshotCapture` future |

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | Shared state for the screenshot capture pipeline |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | The HTTP handler that triggers screenshots |
