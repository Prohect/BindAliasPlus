# McScreenHelper (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public final class com.github.prohect.util.McScreenHelper
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Utility class that bridges the screen-access API difference between
Minecraft 26.1.x and 26.2+.

- **26.1.x**: `Minecraft.screen` (field) and `Minecraft.setScreen(Screen)` (method).
- **26.2+**: Both moved to the `Gui` class, accessed via `client.gui.screen()` and `client.gui.setScreen(Screen)`.

Detection is performed once at class-load time in the [static initializer](static-init.md),
which uses reflection to test whether the `Gui` class has a `screen()` method.
At runtime, `getCurrentScreen()` and `setScreen()` branch on the detected version
and invoke the appropriate reflected member.

Private constructor — not instantiable. All methods are static.
Thread-safe (reflection fields are resolved once and read-only thereafter).

## See Also

| Item                                                                 | Description                    |
| -------------------------------------------------------------------- | ------------------------------ |
| [getCurrentScreen](getCurrentScreen.md)                              | Gets the current screen        |
| [setScreen](setScreen.md)                                            | Sets the current screen        |
| [static-init](static-init.md)                                        | One-time reflection resolution |
| [Alias.getCurrentScreen](../../alias/Alias.java/getCurrentScreen.md) | Convenience wrapper            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
