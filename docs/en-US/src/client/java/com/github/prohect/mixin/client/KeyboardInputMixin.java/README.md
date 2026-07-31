# KeyboardInputMixin

## Fields

| Name | Type | Description |
| ---- | ---- | ----------- |

## Methods

| Name   | Signature                                | Description                                                                        |
| ------ | ---------------------------------------- | ---------------------------------------------------------------------------------- |
| `tick` | `private static void tick(CallbackInfo)` | `@Inject("HEAD")` — processes `KEY_QUEUE` and dispatches bound `AliasWithoutArgs`. |

## See Also

| Item                                                                            | Description                                      |
| ------------------------------------------------------------------------------- | ------------------------------------------------ |
| [KeyBoardMixin](../KeyBoardMixin.java/KeyBoardMixin.md)                         | Enqueues keyboard events                         |
| [MouseMixin](../MouseMixin.java/MouseMixin.md)                                  | Enqueues mouse events                            |
| [BindAliasClient](../../../BindAliasClient.java/BindAliasClient.md) | Main class owning `KEY_QUEUE` and `BINDING_PLUS` |
| [Alias](../../../alias/Alias.java/Alias.md)                                     | Alias registry for dispatch                      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
