# NativeImageMixin

A Mixin for `net.minecraft.client.texture.NativeImage` (Yarn) — the equivalent of `com.mojang.blaze3d.platform.NativeImage` in Mojang mappings. Intercepts `writeTo(Path)` to capture in-memory PNG bytes before they hit disk, enabling the MCP screenshot endpoint to serve screenshots without filesystem I/O.

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [invokeWrite](invokeWrite.md) | `abstract boolean invokeWrite(WritableByteChannel) throws IOException` | Access-widened invoker for `NativeImage.write` (the private PNG encoder; named `writeToChannel` in Mojang mappings) |
| [onWriteTo](onWriteTo.md) | `private void onWriteTo(Path, CallbackInfo)` | `@Inject` at HEAD of `writeTo(Path)`. Intercepts screenshot writes and populates `ScreenshotCapture.nextPngFuture` |

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/ScreenshotCapture.md) | Consumer — holds `nextPngFuture` and `lastPath`/`lastName` for the MCP HTTP handler |
| [McpHttpServer](../../../mcp/McpHttpServer.java/McpHttpServer.md) | MCP HTTP server — polls `nextPngFuture` via the screenshot endpoint |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAlias/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
