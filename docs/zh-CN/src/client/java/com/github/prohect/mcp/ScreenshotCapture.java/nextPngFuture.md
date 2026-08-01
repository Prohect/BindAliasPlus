# nextPngFuture 字段（src/client/java/com/github/prohect/mcp/ScreenshotCapture.java）

## 语法

```java
public static volatile CompletableFuture<byte[]> nextPngFuture
```

## 备注

持有下一次截图内存 PNG 字节的 `CompletableFuture`。由 `McpHttpServer.handleScreenshot` 在触发原版截图前设置，并由 `NativeImageMixin.onWriteToFile` 以 PNG 字节数组完成。由于它由主线程写入、在渲染线程的 mixin 回调中读取，故为 `volatile`。一次性：mixin 消费后将其置为 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [McpHttpServer.handleScreenshot](McpHttpServer.java/handleScreenshot.md) | 设置此 future |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | 完成此 future |
