# McScreenHelper (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public final class com.github.prohect.util.McScreenHelper
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Simple static utility wrapping direct access to `MinecraftClient.currentScreen` (a public field in Yarn mappings; `Minecraft.screen` in Mojang) and `MinecraftClient.setScreen(Screen)`.

The class is `final` with a private constructor — pure static utility. On this branch (Yarn mappings), no reflection or branch detection is needed: `currentScreen` is a public field and `setScreen()` is a public method directly on `MinecraftClient`.

(Yarn: `MinecraftClient.currentScreen`; Mojang: `Minecraft.screen`)

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | Gets the current screen using the detected API |
| [setScreen](setScreen.md) | Sets the current screen using the detected API |
| [static-init](static-init.md) | Reflection-based API detection |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | The primary caller of `getCurrentScreen` |
