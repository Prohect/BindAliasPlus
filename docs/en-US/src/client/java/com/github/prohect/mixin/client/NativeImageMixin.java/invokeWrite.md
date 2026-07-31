# invokeWrite method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Invoker("write")
abstract boolean invokeWrite(WritableByteChannel channel) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `channel` | `WritableByteChannel` | The output channel to write PNG-encoded bytes to |

## Return value

`true` if the PNG was successfully encoded and written to the channel; `false` otherwise (delegates to the underlying STB-image encoder result).

## Remarks

Access-widened `@Invoker` for the private `NativeImage#write(WritableByteChannel)` method. Mixin generates a bridge at compile time that calls the original private method. This requires an access widener in the mod's build configuration (`nativeimage.accesswidener`) that removes the `private` flag from `write`, allowing Mixin to generate the invoker.

The original `write` method uses the STB-image library to encode the native image's pixel data as PNG and writes the encoded bytes to the provided `WritableByteChannel`. The invoker is used by `onWriteTo` to capture the PNG bytes in memory via a `ByteArrayOutputStream` wrapped in a `Channels.newChannel`, avoiding any disk I/O for the MCP screenshot endpoint.

Declared `abstract` because Mixin generates the implementation — the method body is the bridge call, not handwritten code. Throws `IOException` if the channel write fails (propagated from the underlying STB encoder).

The 26.x (Mojang) equivalent was called `invokeWriteToChannel` and targeted `NativeImage#writeToChannel(WritableByteChannel)` — the rename reflects the Yarn mapping where the private method is simply named `write`.

## See Also

| Item | Description |
|------|-------------|
| [onWriteTo](onWriteTo.md) | The caller — captures screenshot PNG bytes to complete the `ScreenshotCapture` future |
| [NativeImageMixin](NativeImageMixin.md) | The enclosing mixin class |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/README.md) | The class whose `nextPngFuture` is completed by the screenshot capture pipeline |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
