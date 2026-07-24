# lastName field (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public static volatile java.lang.String lastName
```

## Remarks

Stores the file name (no directory) of the most recent screenshot, e.g. `2026-07-24_19.30.05.png`. Set by `NativeImageMixin.onWriteToFile` alongside `lastPath`.

Used for display/logging by the MCP HTTP handler. `volatile` for cross-thread visibility.

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
