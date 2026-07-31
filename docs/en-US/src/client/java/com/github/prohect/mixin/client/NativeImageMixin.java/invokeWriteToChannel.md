# invokeWriteToChannel method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Invoker("writeToChannel")
abstract boolean invokeWriteToChannel(WritableByteChannel channel) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `channel` | `java.nio.channels.WritableByteChannel` | The channel to write PNG bytes into |

## Return value

`true` if the PNG was successfully encoded and written; `false` if encoding failed.

## Remarks

An `@Invoker` access-widened bridge for the `private` method `NativeImage.writeToChannel(WritableByteChannel)`. The mod's access widener removes the `private` flag at compile time, allowing Mixin to generate this invoker. At runtime, the call executes the original STB-image PNG encoder on the `NativeImage` instance (`this`). Used exclusively by [`onWriteToFile`](onWriteToFile.md) to capture PNG bytes in memory before they hit disk.

## See Also

| Item | Description |
|------|-------------|
| [onWriteToFile](onWriteToFile.md) | The caller that uses this invoker |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | Where the resulting bytes are delivered |
