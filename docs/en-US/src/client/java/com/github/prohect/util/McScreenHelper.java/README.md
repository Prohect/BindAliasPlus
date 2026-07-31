# McScreenHelper

Simple static utility wrapping direct access to `MinecraftClient.currentScreen` and `MinecraftClient.setScreen()`.

## Fields

_(none — no reflection fields on this branch)_

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen(MinecraftClient client)` | Returns `client.currentScreen` |
| [setScreen](setScreen.md) | `static void setScreen(MinecraftClient client, Screen screen)` | Delegates to `client.setScreen(screen)` |

## See Also

| Item | Description |
|------|-------------|
| [MinecraftClientMixin](../../mixin/client/MinecraftClientMixin.java/README.md) | Primary caller — tracks `currentScreen` every tick |
