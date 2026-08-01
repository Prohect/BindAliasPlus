# onWriteTo 方法（src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java）

## 语法

```java
@Inject(method = "writeTo(Ljava/nio/file/Path;)V", at = @At("HEAD"))
private void onWriteTo(Path file, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `file` | `Path` | 图像正在写入的文件路径 |
| `ci` | `CallbackInfo` | Mixin 回调（未使用——注入不可取消） |

## 备注

注入到 `NativeImage#writeTo(Path)` 的 `HEAD`，这是 Minecraft 将 `NativeImage` 保存到磁盘时调用的方法。这是 MCP 截图 endpoint 的钩子。

**处理步骤：**

1. **截图守卫：** 检查 `file.getParent()`——如果为 `null` 或父目录不名为 `"screenshots"`，立即返回。这确保只拦截截图写入；其他 `NativeImage` 用途（纹理图集、图标等）原样通过。
2. **future 检查：** 读取 `ScreenshotCapture.nextPngFuture`。如果为 `null`（没有进行中的截图请求），返回。否则，原子地将其置为 `null`（一次性消费）。
3. **PNG 编码：** 创建 `ByteArrayOutputStream`，通过 `Channels.newChannel(baos)` 包装为 `WritableByteChannel`，并调用 `invokeWrite(channel)` 在内存中将原生图像编码为 PNG 字节。
4. **future 完成：** 成功时（`invokeWrite` 返回 `true`）：
   - 将绝对路径存入 `ScreenshotCapture.lastPath`。
   - 将文件名存入 `ScreenshotCapture.lastName`。
   - 通过 `f.complete(bytes)` 用 PNG 字节数组完成 future。
   - 失败时（`invokeWrite` 返回 `false`）：用 `null` 完成 future。
   - `IOException` 时：通过 `f.completeExceptionally(e)` 异常完成 future。

这种方法把 MCP 截图响应时间从约 500ms（26.x 方法中的睡眠 + 文件系统扫描）降到 <50ms（GPU 回读 + PNG 编码），因为字节在编码时刻于内存中捕获，而不是等待文件出现在磁盘上再读回。

26.x（Mojang）的等价物名为 `onWriteToFile`，针对 `NativeImage#writeTo(Path)`——重命名反映了 Yarn 映射中该方法名为 `writeTo(Path)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [invokeWrite](invokeWrite.md) | 私有 PNG 编码器的 `@Invoker` |
| [NativeImageMixin](NativeImageMixin.md) | 外层 mixin 类 |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 截图捕获管线的共享状态 |
| [McpHttpServer.handleScreenshot](../../../mcp/McpHttpServer.java/handleScreenshot.md) | 发起截图请求的 HTTP 处理器 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
