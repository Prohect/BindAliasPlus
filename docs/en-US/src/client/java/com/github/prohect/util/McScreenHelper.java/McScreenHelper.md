# McScreenHelper (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public final class com.github.prohect.util.McScreenHelper
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Bridges the screen-access API change between Minecraft 26.1.x and 26.2+:
- **26.1.x**: `Minecraft.screen` (public field) and `Minecraft.setScreen(Screen)` (method).
- **26.2+**: Both were moved into the `Gui` class, accessed via `client.gui.screen()` and `client.gui.setScreen(Screen)`.

Detection is performed once in the static initializer, which uses reflection to check whether the `Gui` class (the type of `Minecraft.gui`) exposes a `screen()` method. The detected code path is cached in static fields and used by `getCurrentScreen()` and `setScreen()`. This avoids compile-time coupling to either API version, allowing a single jar to work across both mappings.

The class is `final` with a private constructor — pure static utility.

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | Gets the current screen using the detected API |
| [setScreen](setScreen.md) | Sets the current screen using the detected API |
| [static-init](static-init.md) | Reflection-based API detection |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | The primary caller of `getCurrentScreen` |
