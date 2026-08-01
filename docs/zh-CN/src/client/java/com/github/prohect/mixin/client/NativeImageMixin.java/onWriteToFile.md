# onWriteToFile 方法（src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java）

## 语法

```java
@Inject(method = "writeToFile(Ljava/nio/file/Path;)V", at = @At("HEAD"))
private void onWriteToFile(Path file, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `file` | `java.nio.file.Path` | 目标文件路径；仅当父目录名为 `screenshots` 时才被拦截 |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入于 `NativeImage#writeToFile(Path)` 的 `HEAD`。处理流程：

1. **父目录检查**：若 `file.getParent()` 为 `null` 或父目录的文件名不是 `"screenshots"`，立即返回 —— 非截图写入不受影响。
2. **future 门控**：读取 `ScreenshotCapture.nextPngFuture`。若为 `null`，则没有截图进行中；返回。
3. **一次性消费**：将 `nextPngFuture` 设为 `null`，使 future 恰好被消费一次。
4. **PNG 编码**：创建 `ByteArrayOutputStream`，用 `WritableByteChannel` 包装它，并调用 `invokeWriteToChannel(channel)` —— 调用原版私有 PNG 编码器的访问拓宽 invoker。
5. **结果送达**：成功时填充 `ScreenshotCapture.lastPath`、`lastName`，并用字节数组完成 future。失败时以异常完成 future。

此后原版 `writeToFile` 调用仍继续执行（未取消），因此截图 PNG 照常写入磁盘。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [invokeWriteToChannel](invokeWriteToChannel.md) | 调用私有 PNG 编码器的 `@Invoker` |
| [ScreenshotCapture.nextPngFuture](../../../mcp/ScreenshotCapture.java/nextPngFuture.md) | 此处完成的 future |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | 设置该 future 的 HTTP 处理器 |
