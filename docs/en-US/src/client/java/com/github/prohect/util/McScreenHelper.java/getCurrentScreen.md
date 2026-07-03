# getCurrentScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static net.minecraft.client.gui.screens.Screen getCurrentScreen(net.minecraft.client.Minecraft)
```

## Parameters

| Name     | Type        | Description                                                  |
| -------- | ----------- | ------------------------------------------------------------ |
| `client` | `Minecraft` | The Minecraft client instance from which to read the screen. |

## Remarks

Returns the currently open `Screen`, or `null` if no screen is open.

Algorithm:

1. If `GUI_HAS_SCREEN` (MC 26.2+): reflectively get the `Gui` object via
   `GUI_FIELD`, then invoke `GUI_SCREEN` on it.
2. Otherwise (MC 26.1.x): reflectively read `MINECRAFT_SCREEN` from `client`.

Throws `RuntimeException` wrapping any reflection failure.

## See Also

| Item                          | Description                              |
| ----------------------------- | ---------------------------------------- |
| [setScreen](setScreen.md)     | The setter counterpart                   |
| [static-init](static-init.md) | Where the reflection fields are resolved |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
