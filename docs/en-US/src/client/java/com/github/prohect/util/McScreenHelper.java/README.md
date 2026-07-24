# McScreenHelper

A utility class bridging Yarn's `MinecraftClient`/`Screen` naming conventions. Provides static helper methods to get and set the current screen, abstracting over Yarn's instance-based API (`client.currentScreen` field and `client.setScreen(Screen)` method) versus Mojang mappings where `setScreen` is a static method on the `Minecraft` class.

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen(MinecraftClient)` | Reads `client.currentScreen` — the currently open screen (or `null` if none) |
| [setScreen](setScreen.md) | `static void setScreen(MinecraftClient, Screen)` | Calls `client.setScreen(screen)` to open or close a GUI screen |

## See Also

| Item | Description |
|------|-------------|
| [Alias.getCurrentScreen](../../alias/Alias.java/getCurrentScreen.md) | Caller — used to check if a screen is open before executing aliases |
| [BindAliasPlusClient.currentScreen](../BindAliasPlusClient.java/currentScreen.md) | Tracks the current screen name string for lock/unlock logic |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
