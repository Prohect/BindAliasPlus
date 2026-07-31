# getCurrentScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static Screen getCurrentScreen(Minecraft client)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `client` | `Minecraft` | The Minecraft client instance |

## Return value

The currently open `Screen`, or `null` if no screen is open.

## Remarks

Gets the current screen using the API version detected at class-load time:
- **26.2+ (GUI_HAS_SCREEN)**: Reflects `gui` field from `Minecraft.class`, then calls `gui.screen()`.
- **26.1.x**: Reflects the `screen` field directly from `Minecraft.class` and reads its value.

Throws `RuntimeException` if reflection fails (e.g., the expected field/method is absent from both API versions). Called by `MinecraftClientMixin.tick()` to update `BindAliasClient.currentScreen` every frame.

## See Also

| Item | Description |
|------|-------------|
| [setScreen](setScreen.md) | The setter counterpart |
| [static-init](static-init.md) | Where `GUI_HAS_SCREEN` and the cached `Field`/`Method` handles are initialized |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | The primary caller |
