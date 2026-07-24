# invokeWriteToChannel method (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Invoker("writeToChannel")
abstract boolean invokeWriteToChannel(WritableByteChannel channel) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `channel` | `WritableByteChannel` | The NIO channel to write PNG bytes into. In practice, a `Channels.newChannel(ByteArrayOutputStream)` wrapper. |

## Remarks

Mixin `@Invoker` that generates a bridge to the private `NativeImage.writeToChannel(WritableByteChannel)` method. The original method encodes the `NativeImage` pixel data as PNG and writes it to the given channel.

The access widener (`bind-alias-plus.accesswidener`) removes the `private` flag from `writeToChannel`, allowing Mixin to generate the invoker. The generated method is abstract — Mixin provides the implementation at compile time that delegates to the original.

Returns `true` on successful encoding, `false` if the image data is invalid.

## See Also

| Item | Description |
|------|-------------|
| [onWriteToFile](onWriteToFile.md) | Calls this invoker to capture PNG bytes |
| [NativeImageMixin](NativeImageMixin.md) | Parent class |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
