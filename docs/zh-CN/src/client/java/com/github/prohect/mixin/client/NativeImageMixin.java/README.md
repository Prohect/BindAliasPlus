# NativeImageMixin

针对 `com.mojang.blaze3d.platform.NativeImage` 的 mixin。拦截截图 PNG 写入以在内存中捕获字节，供 MCP 截图 endpoint 使用，通过访问拓宽的 invoker 调用私有 PNG 编码器。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [invokeWriteToChannel](invokeWriteToChannel.md) | `abstract boolean invokeWriteToChannel(WritableByteChannel channel)` | 私有 `writeToChannel` 的 `@Invoker` —— 到 STB PNG 编码器的访问拓宽桥 |
| [onWriteToFile](onWriteToFile.md) | `void onWriteToFile(Path file, CallbackInfo ci)` | `@Inject` 于 `writeToFile` 的 `HEAD` —— 捕获截图 PNG 字节并完成进行中的 `ScreenshotCapture` future |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 截图捕获管线的共享状态 |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | 触发截图的 HTTP 处理器 |
