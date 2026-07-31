# setScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static void setScreen(Minecraft client, Screen screen)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `client` | `Minecraft` | The Minecraft client instance |
| `screen` | `Screen` | The screen to open; `null` to close the current screen |

## Remarks

Sets the current screen using the API version detected at class-load time:
- **26.2+ (GUI_HAS_SCREEN)**: Reflects `gui` field from `Minecraft.class`, then calls `gui.setScreen(screen)`.
- **26.1.x**: Reflects `Minecraft.setScreen(Screen)` method and invokes it.

Throws `RuntimeException` if reflection fails. Passing `null` as `screen` closes the current screen.

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | The getter counterpart |
| [static-init](static-init.md) | Where `GUI_HAS_SCREEN` and the cached handles are initialized |
