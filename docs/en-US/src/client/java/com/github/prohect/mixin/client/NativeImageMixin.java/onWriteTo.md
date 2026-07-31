# onWriteTo method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Inject(method = "writeTo(Ljava/nio/file/Path;)V", at = @At("HEAD"))
private void onWriteTo(java.nio.file.Path file, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `file` | `java.nio.file.Path` | The destination path for the image file |
| `ci` | `org.spongepowered.asm.mixin.injection.callback.CallbackInfo` | Mixin callback object (unused) |

## Remarks

Injection point at the HEAD of `NativeImage.writeTo(Path)` (Yarn; `writeToFile(Path)` in Mojang mappings). This method intercepts every `writeTo` call and checks whether it is a screenshot. The interception logic:

1. **Guard clause — screenshot check:** If the file's parent directory is not named `screenshots`, the method returns immediately. This prevents interfering with other `NativeImage.writeTo` usage (e.g., icons, textures).

2. **One-shot future:** Reads `ScreenshotCapture.nextPngFuture` and sets it to `null` atomically, ensuring each future is completed at most once.

3. **In-memory PNG capture:** Creates a `ByteArrayOutputStream`, wraps it in a `WritableByteChannel`, and calls [invokeWrite](invokeWrite.md) to encode the PNG data into memory. On success, stores the absolute path and filename in `ScreenshotCapture.lastPath` / `ScreenshotCapture.lastName`, then completes the future with the byte array. On failure, completes the future exceptionally with the `IOException`.

This pipeline cuts screenshot response time from ~500 ms (sleep + filesystem scan) to &lt;50 ms (GPU readback + PNG encode + in-memory capture), because the MCP HTTP handler reads from the `CompletableFuture` instead of polling the filesystem.

## See Also

| Item | Description |
|------|-------------|
| [invokeWrite](invokeWrite.md) | `@Invoker` for `NativeImage.write` — called to capture PNG bytes |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/ScreenshotCapture.md) | Shared state — `nextPngFuture`, `lastPath`, `lastName` |
| [ScreenshotCapture.nextPngFuture](../../../mcp/ScreenshotCapture.java/nextPngFuture.md) | The one-shot future consumed here |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAlias/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
