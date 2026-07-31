# handleScreenshot method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static synchronized void handleScreenshot(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange for `GET /screenshot`. |

## Remarks

`GET /screenshot` — triggers an in-game screenshot and returns it as a base64-encoded PNG with metadata. `synchronized` to prevent concurrent screenshot requests (only one capture pipeline at a time).

**Pipeline**:
1. **Pre-check**: Verifies the player is in-game via `onMainThread()`. Returns 400 if not.
2. **Arm capture**: Creates a `CompletableFuture<byte[]>` and stores it in `ScreenshotCapture.nextPngFuture`. The `NativeImageMixin` will complete this future when the PNG is encoded.
3. **Trigger**: Calls `Screenshot.grab()` on the main thread, which initiates the vanilla screenshot pipeline (GPU readback → NativeImage → PNG encode → `writeToFile`).
4. **Wait**: Blocks on the HTTP thread for up to 3 seconds for the future to complete. The actual PNG encoding happens on an I/O thread.
5. **Return**: On success, returns `{"path":..., "name":..., "base64":...}`. On failure, returns an error object.

The `synchronized` keyword + nulling `nextPngFuture` after consumption ensures only one capture can be in-flight at a time. If the timeout expires, the future is abandoned (but the PNG still writes to disk normally).

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture](../ScreenshotCapture.java/ScreenshotCapture.md) | Shared state for the capture |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | Completes the future with PNG bytes |
| [onMainThread](onMainThread.md) | Thread bridge for the pre-check and trigger |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
