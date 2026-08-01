# NativeImageMixin（src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java）

## 语法

```java
@Mixin(NativeImage.class)
public abstract class com.github.prohect.mixin.client.NativeImageMixin
```

## 静态初始化器

_无。_

## 备注

混入 `com.mojang.blaze3d.platform.NativeImage`，拦截 `writeToFile(Path)` 并在 PNG 字节落盘之前在内存中捕获它们。这是 MCP 截图 endpoint 的钩子：截图时（目标路径的父目录名为 `screenshots`），mixin 通过访问拓宽的 `writeToChannel` invoker 将 `NativeImage` 编码为 PNG 字节，并完成 [`ScreenshotCapture`](../../../mcp/ScreenshotCapture.java/README.md) 中进行中的 `CompletableFuture<byte[]>`。这把 MCP 截图响应时间从约 500 ms（睡眠 + 文件系统扫描）降到 <50 ms（GPU 回读 + PNG 编码）。非截图的 `writeToFile` 调用原样通过，不做修改。

`writeToChannel` 的 `@Invoker` 是访问拓宽的桥：原始的 `NativeImage.writeToChannel(WritableByteChannel)` 是 `private` 的；模组构建配置中的 access widener 移除 `private` 标志，使 Mixin 能生成 invoker。运行时调用仍在 `this` 上执行原始的 STB-image PNG 编码器。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onWriteToFile](onWriteToFile.md) | 捕获 PNG 字节的 `@Inject` |
| [invokeWriteToChannel](invokeWriteToChannel.md) | 私有 `writeToChannel` 方法的 `@Invoker` |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 共享状态类，其 future 由本 mixin 完成 |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | 触发截图的 HTTP 处理器 |
