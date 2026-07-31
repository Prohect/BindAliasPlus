# onWriteTo method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Inject(method = "writeTo(Ljava/nio/file/Path;)V", at = @At("HEAD"))
private void onWriteTo(Path file, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `file` | `Path` | The file path the image is being written to |
| `ci` | `CallbackInfo` | Mixin callback (unused — injection is not cancellable) |

## Remarks

Injected at `HEAD` of `NativeImage#writeTo(Path)`, the method Minecraft calls to save a `NativeImage` to disk. This is the hook for the MCP screenshot endpoint.

**Processing steps:**

1. **Screenshot guard:** Checks `file.getParent()` — if `null` or the parent directory is not named `"screenshots"`, returns immediately. This ensures only screenshot writes are intercepted; other `NativeImage` usage (texture atlases, icons, etc.) passes through unmodified.
2. **Future check:** Reads `ScreenshotCapture.nextPngFuture`. If `null` (no screenshot request in progress), returns. Otherwise, atomically sets it to `null` (one-shot consumption).
3. **PNG encoding:** Creates a `ByteArrayOutputStream`, wraps it in a `WritableByteChannel` via `Channels.newChannel(baos)`, and calls `invokeWrite(channel)` to encode the native image as PNG bytes in memory.
4. **Future completion:** On success (`invokeWrite` returns `true`):
   - Stores the absolute path in `ScreenshotCapture.lastPath`.
   - Stores the filename in `ScreenshotCapture.lastName`.
   - Completes the future with the PNG byte array via `f.complete(bytes)`.
   - On failure (`invokeWrite` returns `false`): completes the future with `null`.
   - On `IOException`: completes the future exceptionally via `f.completeExceptionally(e)`.

This approach cuts MCP screenshot response time from ~500 ms (sleep + filesystem scan in the 26.x approach) to <50 ms (GPU readback + PNG encode), because the bytes are captured in memory at the moment of encoding rather than waiting for the file to appear on disk and then reading it back.

The 26.x (Mojang) equivalent was called `onWriteToFile` and targeted `NativeImage#writeTo(Path)` — the rename reflects the Yarn mapping where the method is `writeTo(Path)`.

## See Also

| Item | Description |
|------|-------------|
| [invokeWrite](invokeWrite.md) | The `@Invoker` for the private PNG encoder |
| [NativeImageMixin](NativeImageMixin.md) | The enclosing mixin class |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | Shared state for the screenshot capture pipeline |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | The HTTP handler that initiates screenshot requests |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
