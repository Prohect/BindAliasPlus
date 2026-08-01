# ScreenshotCapture

`NativeImageMixin` → `McpHttpServer` 截图捕获管线的共享状态。持有用于内存 PNG 字节传输的一次性 future，以及 JSON 响应所需的路径/文件元数据。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [nextPngFuture](nextPngFuture.md) | `volatile CompletableFuture<byte[]>`（静态） | 由 mixin 用内存 PNG 字节完成的一次性 future |
| [lastPath](lastPath.md) | `volatile String`（静态） | 最近一次捕获截图的绝对路径 |
| [lastName](lastName.md) | `volatile String`（静态） | 最近一次捕获截图的文件名 |

## 方法

_无。_

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [McpHttpServer.handleScreenshot](McpHttpServer.java/handleScreenshot.md) | 触发截图的 HTTP 处理器 |
| [NativeImageMixin](../../mixin/client/NativeImageMixin.java/README.md) | 捕获 PNG 字节的 mixin |
