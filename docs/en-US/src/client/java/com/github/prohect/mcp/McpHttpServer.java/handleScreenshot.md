# handleScreenshot method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static synchronized void handleScreenshot(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; response body receives the JSON envelope with base64-encoded PNG |

## Remarks

`GET /screenshot` handler. Synchronized to prevent concurrent screenshot requests (only one screenshot can be in-flight at a time). On the main thread:

1. Calls `StateTracker.begin(false)` to snapshot the pre-screenshot state.
2. Creates a `CompletableFuture<byte[]>` and assigns it to `ScreenshotCapture.nextPngFuture`.
3. Triggers the vanilla screenshot via `ScreenshotRecorder.saveScreenshot()` (which calls `NativeImage#writeTo` → `NativeImageMixin#onWriteTo` → completes the future). (Yarn: `ScreenshotRecorder.saveScreenshot()`; Mojang: `options.takeScreenshot()`)
4. Blocks on the future with a 2500ms timeout.
5. On success, base64-encodes the PNG bytes and includes them in the JSON envelope with `path`, `name`, and `base64` fields.
6. On timeout or failure, returns an error envelope with the reason.

The `synchronized` keyword ensures only one screenshot request processes at a time.

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture.nextPngFuture](ScreenshotCapture.java/nextPngFuture.md) | The future completed by the mixin |
| [NativeImageMixin.onWriteTo](../../mixin/client/NativeImageMixin.java/onWriteTo.md) | The mixin that captures PNG bytes |
| [StateTracker.begin](StateTracker.java/begin.md) | Pre-screenshot state snapshot |
