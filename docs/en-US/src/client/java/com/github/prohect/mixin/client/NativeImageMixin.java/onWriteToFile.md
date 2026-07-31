# onWriteToFile method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Inject(method = "writeToFile(Ljava/nio/file/Path;)V", at = @At("HEAD"))
private void onWriteToFile(Path file, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `file` | `java.nio.file.Path` | The target file path; only intercepted when the parent directory is named `screenshots` |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `NativeImage#writeToFile(Path)`. Processing:

1. **Parent directory check**: if `file.getParent()` is `null` or the parent directory's file name is not `"screenshots"`, returns immediately — non-screenshot writes are untouched.
2. **Future gate**: reads `ScreenshotCapture.nextPngFuture`. If `null`, no screenshot is in progress; returns.
3. **One-shot**: sets `nextPngFuture` to `null` so the future is consumed exactly once.
4. **PNG encoding**: creates a `ByteArrayOutputStream`, wraps it in a `WritableByteChannel`, and calls `invokeWriteToChannel(channel)` — the access-widened invoker that calls vanilla's private PNG encoder.
5. **Result delivery**: on success, populates `ScreenshotCapture.lastPath`, `lastName`, and completes the future with the byte array. On failure, completes the future exceptionally.

The vanilla `writeToFile` call still proceeds after this injection (not cancelled), so the screenshot PNG is written to disk as normal.

## See Also

| Item | Description |
|------|-------------|
| [invokeWriteToChannel](invokeWriteToChannel.md) | The `@Invoker` that calls the private PNG encoder |
| [ScreenshotCapture.nextPngFuture](../../../mcp/ScreenshotCapture.java/nextPngFuture.md) | The future completed here |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | The HTTP handler that sets up the future |
