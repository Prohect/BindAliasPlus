# onWriteToFile method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Inject(method = "writeToFile(Ljava/nio/file/Path;)V", at = @At("HEAD"))
private void onWriteToFile(Path file, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `file` | `Path` | The target file path for the screenshot. Checked to ensure parent directory is named `screenshots`. |
| `ci` | `CallbackInfo` | Mixin callback (unused). |

## Remarks

Injected at the head of `NativeImage.writeToFile`. Performs screenshot capture for the MCP HTTP pipeline.

**Algorithm**:
1. **Guard**: Checks if the file's parent directory name is `"screenshots"`. If not, returns immediately — this avoids intercepting texture loading, icon writing, or other `NativeImage` usage.
2. **Future check**: Reads `ScreenshotCapture.nextPngFuture`. If `null`, no consumer is waiting — returns immediately.
3. **One-shot**: Sets `ScreenshotCapture.nextPngFuture = null` to prevent double-completion.
4. **PNG encode**: Creates a `ByteArrayOutputStream`, wraps it in a `WritableByteChannel`, and calls `invokeWriteToChannel(channel)` — the original STB-image PNG encoder writes to the in-memory buffer.
5. **Metadata**: Stores absolute path and filename in `ScreenshotCapture.lastPath` / `lastName`.
6. **Completion**: If encoding succeeded, completes the future with the byte array. If encoding failed (returned `false`), completes with `null`. On `IOException`, completes exceptionally.

**Latency impact**: This capture runs during the existing `writeToFile` call (which happens on the render thread after a screenshot keypress). The PNG encode overhead is the same as vanilla — the only added cost is the `ByteArrayOutputStream` wrapping (~ negligible). The MCP consumer avoids the previous 500 ms polling delay entirely.

## See Also

| Item | Description |
|------|-------------|
| [invokeWriteToChannel](invokeWriteToChannel.md) | PNG encoder bridge called here |
| [ScreenshotCapture.nextPngFuture](../../mcp/ScreenshotCapture.java/nextPngFuture.md) | Future completed by this method |
| [McpHttpServer](../../mcp/McpHttpServer.java/McpHttpServer.md) | Consumer that polls the future |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
