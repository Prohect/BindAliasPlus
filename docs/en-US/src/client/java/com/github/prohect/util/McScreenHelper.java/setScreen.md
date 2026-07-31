# setScreen method (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Syntax

```java
public static void setScreen(MinecraftClient client, Screen screen)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `client` | `MinecraftClient` | The Minecraft client instance |
| `screen` | `Screen` | The screen to open; `null` to close the current screen |

## Remarks

Delegates to `client.setScreen(screen)` directly. Passing `null` as `screen` closes the current screen.

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | The getter counterpart |
| [static-init](static-init.md) | Where `GUI_HAS_SCREEN` and the cached handles are initialized |
