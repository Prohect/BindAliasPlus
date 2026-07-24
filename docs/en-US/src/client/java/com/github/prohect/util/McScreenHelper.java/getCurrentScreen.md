# getCurrentScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static net.minecraft.client.gui.screen.Screen getCurrentScreen(net.minecraft.client.MinecraftClient client)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `client` | `net.minecraft.client.MinecraftClient` | The client instance (typically `MinecraftClient.getInstance()`) |

## Return value

The currently open `Screen`, or `null` if no screen is open.

## Remarks

A simple accessor that reads `client.currentScreen` — the public field on `MinecraftClient` that holds the currently displayed GUI screen. In Yarn mappings, `currentScreen` is a public field; in Mojang mappings, it is `minecraft.screen` (also a public field but on a differently-named class).

This method exists to provide a consistent API for screen checking that works across Yarn branches. Callers do not need to import `MinecraftClient` or know whether `currentScreen` is a field or accessor — they call `McScreenHelper.getCurrentScreen(client)` instead.

Used by [Alias.getCurrentScreen](../../alias/Alias.java/getCurrentScreen.md) to determine whether the player is on a screen that should suppress alias execution (e.g., text-input screens where keys should go to the text field rather than triggering aliases).

## See Also

| Item | Description |
|------|-------------|
| [setScreen](setScreen.md) | Companion setter — `McScreenHelper.setScreen(client, screen)` |
| [Alias.getCurrentScreen](../../alias/Alias.java/getCurrentScreen.md) | Primary caller — screen-aware alias gating |
| [BindAliasPlusClient.currentScreen](../BindAliasPlusClient.java/currentScreen.md) | Cached screen name string |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
