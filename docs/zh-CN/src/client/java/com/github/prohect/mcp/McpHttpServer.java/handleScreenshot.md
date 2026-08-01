# handleScreenshot 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static synchronized void handleScreenshot(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；响应体接收包含 base64 编码 PNG 的 JSON envelope |

## 备注

`GET /screenshot` 处理器。同步以防止并发截图请求（同一时间只能有一个截图进行中）。在主线程上：

1. 调用 `StateTracker.begin(false)` 快照截图前的状态。
2. 创建 `CompletableFuture<byte[]>` 并赋给 `ScreenshotCapture.nextPngFuture`。
3. 通过 `Minecraft.getInstance().options.takeScreenshot()` 触发原版截图（它调用 `NativeImage#writeToFile` → `NativeImageMixin#onWriteToFile` → 完成 future）。
4. 以 2500ms 超时阻塞等待 future。
5. 成功时，将 PNG 字节 base64 编码，并以 `path`、`name` 和 `base64` 字段包含在 JSON envelope 中。
6. 超时或失败时，返回带原因的 error envelope。

`synchronized` 关键字确保同一时间只处理一个截图请求。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ScreenshotCapture.nextPngFuture](ScreenshotCapture.java/nextPngFuture.md) | 由 mixin 完成的 future |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | 捕获 PNG 字节的 mixin |
| [StateTracker.begin](StateTracker.java/begin.md) | 截图前的状态快照 |
