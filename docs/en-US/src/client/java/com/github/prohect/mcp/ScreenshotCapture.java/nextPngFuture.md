# nextPngFuture field (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public static volatile java.util.concurrent.CompletableFuture<byte[]> nextPngFuture
```

## Remarks

A one-shot future used by the screenshot capture pipeline. The consumer (`McpHttpServer`) sets this field to a new `CompletableFuture` before requesting a screenshot. The producer (`NativeImageMixin.onWriteToFile`) completes it with the in-memory PNG bytes when the next screenshot is saved.

After completion, the producer sets this field to `null` to prevent re-consumption. `volatile` ensures visibility across the render thread (producer) and the HTTP handler thread (consumer).

If the PNG encoding fails, the future is completed exceptionally with the `IOException`.

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
