# GuiMixin

## Fields

| Name | Type | Description |
| ---- | ---- | ----------- |

## Methods

| Name          | Signature                                        | Description                                                                         |
| ------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------- |
| `onSetScreen` | `private void onSetScreen(Screen, CallbackInfo)` | `@Inject("RETURN")` — tracks current screen in `BindAliasPlusClient.currentScreen`. |

## See Also

| Item                                                                            | Description                            |
| ------------------------------------------------------------------------------- | -------------------------------------- |
| [BindAliasPlusClient](../../../BindAliasPlusClient.java/BindAliasPlusClient.md) | Main class owning `currentScreen`      |
| [Alias](../../../alias/Alias.java/Alias.md)                                     | Uses `currentScreen` for screen guards |
| [KeyboardInputMixin](../KeyboardInputMixin.java/KeyboardInputMixin.md)          | Key-binding dispatch mixin             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
