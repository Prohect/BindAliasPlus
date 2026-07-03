# KeyBoardMixin

## Fields

| Name | Type | Description |
| ---- | ---- | ----------- |

## Methods

| Name    | Signature                                               | Description                                                           |
| ------- | ------------------------------------------------------- | --------------------------------------------------------------------- |
| `onKey` | `private void onKey(long, int, KeyEvent, CallbackInfo)` | `@Inject("HEAD")` — enqueues `KeyPressed` records for mod-bound keys. |

## See Also

| Item                                                                            | Description                                      |
| ------------------------------------------------------------------------------- | ------------------------------------------------ |
| [KeyboardInputMixin](../KeyboardInputMixin.java/KeyboardInputMixin.md)          | Consumes the queue                               |
| [MouseMixin](../MouseMixin.java/MouseMixin.md)                                  | Same pattern for mouse events                    |
| [BindAliasPlusClient](../../../BindAliasPlusClient.java/BindAliasPlusClient.md) | Main class owning `KEY_QUEUE` and `BINDING_PLUS` |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/LockAlias.md)            | Key lock mechanism                               |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
