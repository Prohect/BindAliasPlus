# NativeImageMixin (src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java)

## Syntax

```java
@Mixin(net.minecraft.client.texture.NativeImage.class)
public abstract class com.github.prohect.mixin.client.NativeImageMixin
```

## Static Initializer

_None._

## Remarks

A Mixin targeting `net.minecraft.client.texture.NativeImage` (Yarn mappings; `com.mojang.blaze3d.platform.NativeImage` in Mojang). This mixin provides two members:

- An `@Invoker("write")` bridge to the private PNG encoder method (named `writeToChannel` in Mojang, `write` in Yarn).
- An `@Inject` at HEAD of `writeTo(Path)` (`writeToFile` in Mojang) that intercepts screenshot writes.

When an `onWriteTo` invocation detects that the target path's parent directory is named `screenshots`, it captures the PNG bytes in memory via the `invokeWrite` invoker and completes `ScreenshotCapture.nextPngFuture`, bypassing filesystem I/O for the MCP screenshot endpoint.

The class is declared `abstract` because it contains an `@Invoker` method without a body (Mixin generates the implementation).

## See Also

| Item | Description |
|------|-------------|
| [invokeWrite](invokeWrite.md) | `@Invoker` for `NativeImage.write` — the private PNG encoder |
| [onWriteTo](onWriteTo.md) | `@Inject` at `writeTo(Path)` — screenshot capture logic |
| [ScreenshotCapture](../../../mcp/ScreenshotCapture.java/ScreenshotCapture.md) | Shared state container populated by this mixin |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
