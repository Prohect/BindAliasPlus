# lastPath field (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public static volatile java.lang.String lastPath
```

## Remarks

Stores the absolute filesystem path of the most recent screenshot. Set by `NativeImageMixin.onWriteToFile` immediately after the PNG bytes are captured into `nextPngFuture`.

Used by the MCP HTTP handler to report the file location in the screenshot response. `volatile` for cross-thread visibility.

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
