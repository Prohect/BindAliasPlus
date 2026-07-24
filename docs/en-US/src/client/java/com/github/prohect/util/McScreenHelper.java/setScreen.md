# setScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static void setScreen(net.minecraft.client.MinecraftClient client, net.minecraft.client.gui.screen.Screen screen)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `client` | `net.minecraft.client.MinecraftClient` | The client instance (typically `MinecraftClient.getInstance()`) |
| `screen` | `net.minecraft.client.gui.screen.Screen` | The screen to open, or `null` to close the current screen and return to gameplay |

## Remarks

A thin wrapper around `client.setScreen(screen)`, the instance method on `MinecraftClient` that controls which GUI screen is displayed. Passing `null` closes the current screen (equivalent to pressing Escape).

**Yarn vs. Mojang:** In Yarn mappings, `setScreen` is an instance method on `MinecraftClient`. In Mojang mappings, `setScreen` is a static method on the `Minecraft` class. This helper abstracts that difference — callers use `McScreenHelper.setScreen(client, screen)` regardless of the mapping flavor, keeping branch-specific imports out of shared code.

Used internally for alias-triggered screen changes (e.g., aliases that open inventory or other GUI screens).

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | Companion getter — `McScreenHelper.getCurrentScreen(client)` |
| [McScreenHelper](McScreenHelper.md) | Class overview |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
