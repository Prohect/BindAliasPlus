# ScreenshotCapture（src/client/java/com/github/prohect/mcp/ScreenshotCapture.java）

## 语法

```java
public final class com.github.prohect.mcp.ScreenshotCapture
```

## 静态初始化

_无。_

## 备注

`NativeImageMixin` → `McpHttpServer` 截图捕获管线的共享状态。静态字段放在这里而非 mixin 类自身（Mixin 框架禁止 mixin 类上的非私有静态成员），且本类刻意位于 mixin 包之外，使 Mixin 注解处理器不会尝试转换它。

管线流程：`McpHttpServer.handleScreenshot` 设置 `nextPngFuture` 并触发原版截图；`NativeImageMixin.onWriteToFile` 拦截 PNG 写入，在内存中编码字节，并用 PNG 数据完成 future。`lastPath` 与 `lastName` 记录文件路径与名称，供 MCP JSON 响应使用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [McpHttpServer.handleScreenshot](McpHttpServer.java/handleScreenshot.md) | 设置 future 并触发截图 |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | 用 PNG 字节完成 future |
| [nextPngFuture](nextPngFuture.md) | 用于内存传输的 future |
| [lastPath](lastPath.md) | 响应的路径元数据 |
| [lastName](lastName.md) | 响应的文件名元数据 |
