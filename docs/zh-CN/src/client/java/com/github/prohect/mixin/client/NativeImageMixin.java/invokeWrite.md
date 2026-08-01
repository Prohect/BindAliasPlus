# invokeWrite 方法（src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java）

## 语法

```java
@Invoker("write")
abstract boolean invokeWrite(WritableByteChannel channel) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `channel` | `WritableByteChannel` | 要写入 PNG 编码字节的输出 channel |

## 返回值

PNG 成功编码并写入 channel 时为 `true`；否则为 `false`（委托给底层 STB-image 编码器的结果）。

## 备注

私有 `NativeImage#write(WritableByteChannel)` 方法的访问放宽 `@Invoker`。Mixin 在编译时生成调用原始私有方法的桥接。这需要模组构建配置中的访问放宽器（`nativeimage.accesswidener`）移除 `write` 的 `private` 标志，使 Mixin 能生成 invoker。

原始 `write` 方法使用 STB-image 库将原生图像的像素数据编码为 PNG，并将编码字节写入提供的 `WritableByteChannel`。`onWriteTo` 使用该 invoker 通过包装在 `Channels.newChannel` 中的 `ByteArrayOutputStream` 在内存中捕获 PNG 字节，为 MCP 截图 endpoint 避免任何磁盘 I/O。

声明为 `abstract` 是因为 Mixin 生成实现——方法体是桥接调用，不是手写代码。如果 channel 写入失败则抛出 `IOException`（从底层 STB 编码器传播）。

26.x（Mojang）的等价物名为 `invokeWriteToChannel`，针对 `NativeImage#writeToChannel(WritableByteChannel)`——重命名反映了 Yarn 映射中私有方法仅名为 `write`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onWriteTo](onWriteTo.md) | 调用方——捕获截图 PNG 字节以完成 `ScreenshotCapture` future |
| [NativeImageMixin](NativeImageMixin.md) | 外层 mixin 类 |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 其 `nextPngFuture` 由截图捕获管线完成的类 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
