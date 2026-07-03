# onSetScreen method (src/client/java/com/github/prohect/mixin/client/GuiMixin.java)

## Syntax

```java
private void onSetScreen(net.minecraft.client.gui.screens.Screen, org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name     | Type           | Description                                                 |
| -------- | -------------- | ----------------------------------------------------------- |
| `screen` | `Screen`       | The screen being set (may be `null` when closing a screen). |
| `ci`     | `CallbackInfo` | Mixin callback info (unused).                               |

## Remarks

Stores the current screen reference in `BindAliasPlusClient.currentScreen` for use by alias dispatch logic.

Algorithm:

1. Receives the `Screen` parameter from `Gui.setScreen()` after it has been set.
2. Assigns `screen` to `BindAliasPlusClient.currentScreen`.

Side effects: updates the global `currentScreen` field. When `screen` is `null`, it clears the reference (no screen open). When non-null, alias dispatch can check `isUnderTextInputScreen()` and screen blacklists.

Callers: called by the Mixin framework whenever `Gui.setScreen()` returns. This is the game's only screen management entry point — every screen open, close, or switch calls this method.

## See Also

| Item                                                                                    | Description                      |
| --------------------------------------------------------------------------------------- | -------------------------------- |
| [GuiMixin](GuiMixin.md)                                                                 | Owning mixin class               |
| [BindAliasPlusClient.currentScreen](../../../BindAliasPlusClient.java/currentScreen.md) | The field updated by this method |
| [Alias.isUnderTextInputScreen](../../../alias/Alias.java/isUnderTextInputScreen.md)     | Gate that reads `currentScreen`  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
