# NativeImageMixin (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Mixin(NativeImage.class)
public abstract class com.github.prohect.mixin.client.NativeImageMixin
```

## Static Initializer

_None._

## Remarks

Mixin that intercepts `NativeImage.writeToFile(Path)` to capture screenshot PNG bytes in memory before they hit disk. This enables the MCP `/screenshot` endpoint to serve the image with sub-50 ms latency instead of polling the filesystem (~500 ms sleep + scan).

**Mechanism**:
1. The `@Invoker` on `writeToChannel` provides Mixin-generated access to the private `NativeImage.writeToChannel(WritableByteChannel)` method, which encodes the image as PNG.
2. The `@Inject` on `writeToFile` intercepts at HEAD. It checks if the target path is under a `screenshots/` directory to avoid intercepting other `NativeImage` usage (e.g., texture loading).
3. If a `CompletableFuture<byte[]>` is waiting in `ScreenshotCapture.nextPngFuture`, the mixin encodes PNG bytes into a `ByteArrayOutputStream` via the invoker, completes the future, and stores file metadata in `ScreenshotCapture.lastPath`/`lastName`.

The `abstract` modifier is required — Mixin generates the implementation of the `@Invoker` method at compile time.

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture](../../mcp/ScreenshotCapture.java/ScreenshotCapture.md) | Shared state consumed by this mixin |
| [McpHttpServer](../../mcp/McpHttpServer.java/McpHttpServer.md) | HTTP handler that polls `nextPngFuture` |
| [invokeWriteToChannel](invokeWriteToChannel.md) | Access-widened PNG encoder bridge |
| [onWriteToFile](onWriteToFile.md) | Injection that performs the capture |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
