# McScreenHelper

Utility class bridging the screen-access API change between Minecraft 26.1.x (`Minecraft.screen` field) and 26.2+ (`Minecraft.gui.screen()` method). Uses reflection in a static initializer to detect which API is available at runtime.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `GUI_HAS_SCREEN` | `boolean` (static, private) | `true` if the Gui class exposes `screen()` (26.2+), `false` for 26.1.x |
| `GUI_FIELD` | `Field` (static, private) | Reflected `Minecraft.gui` field handle |
| `GUI_SCREEN` | `Method` (static, private) | Reflected `Gui.screen()` method handle (null on 26.1.x) |
| `GUI_SET_SCREEN` | `Method` (static, private) | Reflected `Gui.setScreen(Screen)` method handle (null on 26.1.x) |
| `MINECRAFT_SCREEN` | `Field` (static, private) | Reflected `Minecraft.screen` field handle (null on 26.2+) |
| `MINECRAFT_SET_SCREEN` | `Method` (static, private) | Reflected `Minecraft.setScreen(Screen)` method handle (null on 26.2+) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen(Minecraft client)` | Gets the current screen via the detected API |
| [setScreen](setScreen.md) | `static void setScreen(Minecraft client, Screen screen)` | Sets the current screen via the detected API |

## See Also

| Item | Description |
|------|-------------|
| [MinecraftClientMixin](../../mixin/client/MinecraftClientMixin.java/README.md) | Primary caller — tracks `currentScreen` every tick |
