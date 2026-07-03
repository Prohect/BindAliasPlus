# setScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static void setScreen(net.minecraft.client.Minecraft, net.minecraft.client.gui.screens.Screen)
```

## Parameters

| Name     | Type        | Description                                               |
| -------- | ----------- | --------------------------------------------------------- |
| `client` | `Minecraft` | The Minecraft client instance on which to set the screen. |
| `screen` | `Screen`    | The screen to open. `null` to close the current screen.   |

## Remarks

Sets the current screen using the appropriate API path for the detected MC version.

Algorithm:

1. If `GUI_HAS_SCREEN` (MC 26.2+): reflectively get the `Gui` object via
   `GUI_FIELD`, then invoke `GUI_SET_SCREEN` with `screen`.
2. Otherwise (MC 26.1.x): reflectively invoke `MINECRAFT_SET_SCREEN` on `client`.

Throws `RuntimeException` wrapping any reflection failure.

## See Also

| Item                                    | Description                              |
| --------------------------------------- | ---------------------------------------- |
| [getCurrentScreen](getCurrentScreen.md) | The getter counterpart                   |
| [static-init](static-init.md)           | Where the reflection fields are resolved |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
