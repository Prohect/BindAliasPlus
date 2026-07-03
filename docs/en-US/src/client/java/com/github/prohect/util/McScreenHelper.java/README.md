# McScreenHelper

## Fields

_All fields are private static final; initialized in the `static` block._

| Name                   | Type      | Description                                                                                               |
| ---------------------- | --------- | --------------------------------------------------------------------------------------------------------- |
| `GUI_HAS_SCREEN`       | `boolean` | `true` if the `Gui` class exposes `screen()` (MC 26.2+); `false` if using Minecraft directly (MC 26.1.x). |
| `GUI_FIELD`            | `Field`   | Reflected `Minecraft.gui` field.                                                                          |
| `GUI_SCREEN`           | `Method`  | Reflected `Gui.screen()` method (26.2+ path).                                                             |
| `GUI_SET_SCREEN`       | `Method`  | Reflected `Gui.setScreen(Screen)` method (26.2+ path).                                                    |
| `MINECRAFT_SCREEN`     | `Field`   | Reflected `Minecraft.screen` field (26.1.x fallback).                                                     |
| `MINECRAFT_SET_SCREEN` | `Method`  | Reflected `Minecraft.setScreen(Screen)` method (26.1.x fallback).                                         |

## Methods

| Name               | Signature                                   | Description                                                          |
| ------------------ | ------------------------------------------- | -------------------------------------------------------------------- |
| `getCurrentScreen` | `static Screen getCurrentScreen(Minecraft)` | Returns the current open screen via the appropriate reflection path. |
| `setScreen`        | `static void setScreen(Minecraft, Screen)`  | Sets the current screen via the appropriate reflection path.         |

## See Also

| Item                                                        | Description                                 |
| ----------------------------------------------------------- | ------------------------------------------- |
| [static-init](static-init.md)                               | One-time reflection field/method resolution |
| [Alias.getCurrentScreen](../Alias.java/getCurrentScreen.md) | Convenience wrapper around this helper      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
