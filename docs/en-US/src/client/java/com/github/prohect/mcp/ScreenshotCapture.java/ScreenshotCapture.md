# ScreenshotCapture (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public final class com.github.prohect.mcp.ScreenshotCapture
```

## Static Initializer

_None._

## Remarks

Shared state container for the screenshot capture pipeline between `NativeImageMixin` (producer) and `McpHttpServer` (consumer). This class holds static `volatile` fields that the mixin writes and the MCP HTTP handler reads.

Deliberately placed **outside** the `mixin` package — Mixin does not transform classes in this package, avoiding class-loading conflicts. The private constructor prevents instantiation; all access is via static fields.

## See Also

| Item | Description |
|------|-------------|
| [NativeImageMixin](../../mixin/client/NativeImageMixin.java/NativeImageMixin.md) | Producer — intercepts `NativeImage.writeToFile` and populates fields here |
| [McpHttpServer](../McpHttpServer.java/McpHttpServer.md) | Consumer — reads `nextPngFuture` to serve screenshot bytes over HTTP |
| [ChatCapture](../ChatCapture.java/ChatCapture.md) | Companion capture class for chat messages |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
