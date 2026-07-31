# NativeImageMixin (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Mixin(NativeImage.class)
public abstract class com.github.prohect.mixin.client.NativeImageMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `com.mojang.blaze3d.platform.NativeImage` to intercept `writeToFile(Path)` and capture PNG bytes in memory before they hit disk. This is the hook for the MCP screenshot endpoint: when a screenshot is taken (the target path's parent directory is named `screenshots`), the mixin encodes the `NativeImage` to PNG bytes via an access-widened `writeToChannel` invoker and completes the in-progress `CompletableFuture<byte[]>` in [`ScreenshotCapture`](../../../mcp/ScreenshotCapture.java/README.md). This cuts MCP screenshot response time from ~500 ms (sleep + filesystem scan) to <50 ms (GPU readback + PNG encode). Non-screenshot `writeToFile` calls pass through unmodified.

The `@Invoker` for `writeToChannel` is an access-widened bridge: the original `NativeImage.writeToChannel(WritableByteChannel)` is `private`; an access widener in the mod's build configuration removes the `private` flag so Mixin can generate the invoker. The runtime call still executes the original STB-image PNG encoder on `this`.

## See Also

| Item | Description |
|------|-------------|
| [onWriteToFile](onWriteToFile.md) | The `@Inject` that captures PNG bytes |
| [invokeWriteToChannel](invokeWriteToChannel.md) | The `@Invoker` for the private `writeToChannel` method |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | The shared state class whose future is completed by this mixin |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | The HTTP handler that triggers screenshots |
