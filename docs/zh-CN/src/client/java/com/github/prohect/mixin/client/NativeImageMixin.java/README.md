# NativeImageMixin

针对 `net.minecraft.client.texture.NativeImage` 的 mixin。拦截截图 PNG 写入以在内存中捕获字节供 MCP 截图 endpoint 使用，通过访问放宽的 invoker 调用私有 PNG 编码器。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [invokeWrite](invokeWrite.md) | `abstract boolean invokeWrite(WritableByteChannel channel)` | 私有 `write` 的 `@Invoker`——到 STB PNG 编码器的访问放宽桥接 |
| [onWriteTo](onWriteTo.md) | `void onWriteTo(Path file, CallbackInfo ci)` | `writeTo` 的 `HEAD` 处 `@Inject`——捕获截图 PNG 字节并完成进行中的 `ScreenshotCapture` future |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 截图捕获管线的共享状态 |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | 触发截图的 HTTP 处理器 |
