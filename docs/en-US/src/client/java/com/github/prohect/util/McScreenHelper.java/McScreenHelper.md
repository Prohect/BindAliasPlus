# McScreenHelper (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public final class com.github.prohect.util.McScreenHelper
```

## Static Initializer

_None._

## Remarks

A minimal utility class that abstracts over Yarn mapping differences for screen management. In Yarn mappings:

- The client class is `net.minecraft.client.MinecraftClient` (Mojang: `net.minecraft.client.Minecraft`).
- The screen class is `net.minecraft.client.gui.screen.Screen` (same name in both, but different package in Mojang: `net.minecraft.client.gui.screens.Screen`).
- The current screen is exposed via the public field `MinecraftClient.currentScreen` and the instance method `MinecraftClient.setScreen(Screen)` — there is no static `Minecraft.setScreen(Screen)` as in Mojang mappings.

This class wraps these Yarn-specific API calls into two static methods: [getCurrentScreen](getCurrentScreen.md) (reads `client.currentScreen`) and [setScreen](setScreen.md) (calls `client.setScreen(screen)`). The private constructor enforces non-instantiability — all access is via static methods.

It is used primarily by [Alias.getCurrentScreen](../../alias/Alias.java/getCurrentScreen.md) for screen-aware alias execution (e.g., preventing alias execution on text-input screens or inventory screens).

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen(MinecraftClient)` |
| [setScreen](setScreen.md) | `static void setScreen(MinecraftClient, Screen)` |
| [Alias.getCurrentScreen](../../alias/Alias.java/getCurrentScreen.md) | Primary caller |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
