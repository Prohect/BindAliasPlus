# NativeImageMixin (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Mixin(NativeImage.class)
public abstract class com.github.prohect.mixin.client.NativeImageMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.texture.NativeImage` to intercept `writeTo(Path)` and capture PNG bytes in memory before they hit disk. This is the hook for the MCP screenshot endpoint: when a screenshot is taken (the target path's parent directory is named `screenshots`), the mixin encodes the `NativeImage` to PNG bytes via an access-widened `write` invoker and completes the in-progress `CompletableFuture<byte[]>` in [`ScreenshotCapture`](../../../mcp/ScreenshotCapture.java/README.md). This cuts MCP screenshot response time from ~500 ms (sleep + filesystem scan) to <50 ms (GPU readback + PNG encode). Non-screenshot `writeTo` calls pass through unmodified.

The `@Invoker` for `write` is an access-widened bridge: the original `NativeImage.write(WritableByteChannel)` is `private`; an access widener in the mod's build configuration removes the `private` flag so Mixin can generate the invoker. The runtime call still executes the original STB-image PNG encoder on `this`.

## See Also

| Item | Description |
|------|-------------|
| [onWriteTo](onWriteTo.md) | The `@Inject` that captures PNG bytes |
| [invokeWrite](invokeWrite.md) | The `@Invoker` for the private `write` method |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | The shared state class whose future is completed by this mixin |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | The HTTP handler that triggers screenshots |
