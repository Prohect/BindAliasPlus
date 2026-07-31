# getCurrentScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static Screen getCurrentScreen(MinecraftClient client)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `client` | `MinecraftClient` | The Minecraft client instance |

## Return value

The currently open `Screen`, or `null` if no screen is open.

## Remarks

Returns `client.currentScreen` directly — a public field on `MinecraftClient` in Yarn mappings (equivalent to `Minecraft.screen` in Mojang mappings). Called by `MinecraftClientMixin.tick()` to update `BindAliasClient.currentScreen` every frame.

## See Also

| Item | Description |
|------|-------------|
| [setScreen](setScreen.md) | The setter counterpart |
| [static-init](static-init.md) | Where `GUI_HAS_SCREEN` and the cached `Field`/`Method` handles are initialized |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | The primary caller |
