# ScreenshotCapture

Shared state for the `NativeImageMixin` → `McpHttpServer` screenshot capture pipeline. Holds the one-shot future for in-memory PNG byte transfer and path/file metadata for the JSON response.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [nextPngFuture](nextPngFuture.md) | `volatile CompletableFuture<byte[]>` (static) | One-shot future completed by the mixin with in-memory PNG bytes |
| [lastPath](lastPath.md) | `volatile String` (static) | Absolute path of the last captured screenshot |
| [lastName](lastName.md) | `volatile String` (static) | File name of the last captured screenshot |

## Methods

_None._

## See Also

| Item | Description |
|------|-------------|
| [McpHttpServer.handleScreenshot](McpHttpServer.java/handleScreenshot.md) | The HTTP handler that triggers screenshots |
| [NativeImageMixin](../../mixin/client/NativeImageMixin.java/README.md) | The mixin that captures PNG bytes |
