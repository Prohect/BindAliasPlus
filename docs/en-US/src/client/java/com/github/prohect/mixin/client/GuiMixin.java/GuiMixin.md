# GuiMixin (src/client/java/com/github/prohect/mixin/client/GuiMixin.java)

## Syntax

```java
public class com.github.prohect.mixin.client.GuiMixin
```

## Static Initializer

_None._

## Remarks

Mixin that injects into `net.minecraft.client.gui.Gui.setScreen()` to track the currently open screen.

**Injection point**: `@Inject(at = @At("RETURN"), method = "setScreen")` — fires after the screen has been set, ensuring `screen` is the newly active screen.

**Purpose**: `BindAliasPlusClient.currentScreen` is used by alias dispatch logic to determine whether a screen is open, enabling screen-blacklist checks and text-input-screen guards. This is the only mechanism for the mod to know the current GUI state.

Lifecycle: the mixin is applied once at class-load time; `onSetScreen` is called every time a screen opens or closes (including `null` for closing).

Thread safety: not thread-safe; `currentScreen` is read and written on the render thread only.

## See Also

| Item                                                                                    | Description                                      |
| --------------------------------------------------------------------------------------- | ------------------------------------------------ |
| [BindAliasPlusClient.currentScreen](../../../BindAliasPlusClient.java/currentScreen.md) | The field this mixin updates                     |
| [Alias.isUnderTextInputScreen](../../../alias/Alias.java/isUnderTextInputScreen.md)     | Uses `currentScreen` to gate key events          |
| [Alias.isUnderAnyScreen](../../../alias/Alias.java/isUnderAnyScreen.md)                 | Uses `currentScreen` for screen-blacklist checks |
| [onSetScreen](onSetScreen.md)                                                           | The injected callback method                     |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
