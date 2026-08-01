# NativeImageMixin（src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java）

## 语法

```java
@Mixin(NativeImage.class)
public abstract class com.github.prohect.mixin.client.NativeImageMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.texture.NativeImage` 以拦截 `writeTo(Path)`，在 PNG 字节落盘前于内存中捕获。这是 MCP 截图 endpoint 的钩子：截图时（目标路径的父目录名为 `screenshots`），mixin 通过访问放宽（access-widened）的 `write` invoker 将 `NativeImage` 编码为 PNG 字节，并完成 [`ScreenshotCapture`](../../../mcp/ScreenshotCapture.java/README.md) 中进行中的 `CompletableFuture<byte[]>`。这把 MCP 截图响应时间从约 500ms（睡眠 + 文件系统扫描）降到 <50ms（GPU 回读 + PNG 编码）。非截图 `writeTo` 调用原样通过。

`write` 的 `@Invoker` 是访问放宽的桥接：原始的 `NativeImage.write(WritableByteChannel)` 是 `private`；模组构建配置中的访问放宽器移除 `private` 标志，使 Mixin 能生成 invoker。运行时调用仍然在 `this` 上执行原始的 STB-image PNG 编码器。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onWriteTo](onWriteTo.md) | 捕获 PNG 字节的 `@Inject` |
| [invokeWrite](invokeWrite.md) | 私有 `write` 方法的 `@Invoker` |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 其 future 由此 mixin 完成的共享状态类 |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | 触发截图的 HTTP 处理器 |
