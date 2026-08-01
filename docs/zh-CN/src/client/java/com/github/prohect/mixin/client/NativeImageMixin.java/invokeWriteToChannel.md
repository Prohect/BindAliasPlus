# invokeWriteToChannel 方法（src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java）

## 语法

```java
@Invoker("writeToChannel")
abstract boolean invokeWriteToChannel(WritableByteChannel channel) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `channel` | `java.nio.channels.WritableByteChannel` | 要将 PNG 字节写入的 channel |

## 返回值

PNG 成功编码并写入返回 `true`；编码失败返回 `false`。

## 备注

私有方法 `NativeImage.writeToChannel(WritableByteChannel)` 的 `@Invoker` 访问拓宽桥。模组的 access widener 在编译时移除 `private` 标志，使 Mixin 能生成此 invoker。运行时调用在 `NativeImage` 实例（`this`）上执行原始的 STB-image PNG 编码器。仅由 [`onWriteToFile`](onWriteToFile.md) 使用，以在 PNG 字节落盘之前在内存中捕获它们。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onWriteToFile](onWriteToFile.md) | 使用此 invoker 的调用方 |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | 结果字节送达的位置 |
