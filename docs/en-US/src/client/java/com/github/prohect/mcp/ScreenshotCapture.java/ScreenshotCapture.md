# ScreenshotCapture (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public final class com.github.prohect.mcp.ScreenshotCapture
```

## Static Initializer

_None._

## Remarks

Shared state for the `NativeImageMixin` → `McpHttpServer` screenshot capture pipeline. Static fields live here rather than on the mixin class itself (Mixin framework forbids non-private static members on mixin classes), and this class is deliberately outside the mixin package so the Mixin annotation processor does not try to transform it.

The pipeline: `McpHttpServer.handleScreenshot` sets `nextPngFuture` and triggers a vanilla screenshot; `NativeImageMixin.onWriteToFile` intercepts the PNG write, encodes bytes in memory, and completes the future with the PNG data. `lastPath` and `lastName` record the file path and name for inclusion in the MCP JSON response.

## See Also

| Item | Description |
|------|-------------|
| [McpHttpServer.handleScreenshot](McpHttpServer.java/handleScreenshot.md) | Sets up the future and triggers screenshots |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | Completes the future with PNG bytes |
| [nextPngFuture](nextPngFuture.md) | The future used for in-memory transfer |
| [lastPath](lastPath.md) | Path metadata for the response |
| [lastName](lastName.md) | Filename metadata for the response |
