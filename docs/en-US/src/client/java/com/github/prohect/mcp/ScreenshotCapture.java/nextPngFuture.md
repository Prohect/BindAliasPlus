# nextPngFuture field (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public static volatile CompletableFuture<byte[]> nextPngFuture
```

## Remarks

A `CompletableFuture` holding the in-memory PNG bytes of the next screenshot. Set by `McpHttpServer.handleScreenshot` before triggering the vanilla screenshot, and completed by `NativeImageMixin.onWriteToFile` with the PNG byte array. `volatile` because it is written from the main thread and read from the mixin callback on the render thread. One-shot: the mixin sets it to `null` after consuming it.

## See Also

| Item | Description |
|------|-------------|
| [McpHttpServer.handleScreenshot](McpHttpServer.java/handleScreenshot.md) | Sets this future |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | Completes this future |
