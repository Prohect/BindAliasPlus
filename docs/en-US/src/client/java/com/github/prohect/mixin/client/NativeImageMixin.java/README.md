# NativeImageMixin

Mixin targeting `net.minecraft.client.texture.NativeImage`. Intercepts screenshot PNG writes to capture bytes in memory for the MCP screenshot endpoint, using an access-widened invoker to the private PNG encoder.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [invokeWrite](invokeWrite.md) | `abstract boolean invokeWrite(WritableByteChannel channel)` | `@Invoker` for the private `write` — access-widened bridge to the STB PNG encoder |
| [onWriteTo](onWriteTo.md) | `void onWriteTo(Path file, CallbackInfo ci)` | `@Inject` at `HEAD` of `writeTo` — captures screenshot PNG bytes and completes the in-progress `ScreenshotCapture` future |

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | Shared state for the screenshot capture pipeline |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | The HTTP handler that triggers screenshots |
