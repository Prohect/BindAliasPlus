# MouseMixin

## Fields

| Name | Type | Description |
| ---- | ---- | ----------- |

## Methods

| Name            | Signature                                                              | Description                                                             |
| --------------- | ---------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `onMouseButton` | `private void onMouseButton(long, MouseButtonInfo, int, CallbackInfo)` | `@Inject("HEAD")` — enqueues mouse button events for mod-bound buttons. |
| `lockCursor`    | `private void lockCursor(CallbackInfo)`                                | `@Inject("RETURN")` — re-applies held key states after cursor grab.     |

## See Also

| Item                                                                                                          | Description                                       |
| ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------- |
| [KeyBoardMixin](../KeyBoardMixin.java/KeyBoardMixin.md)                                                       | Same pattern for keyboard events                  |
| [KeyboardInputMixin](../KeyboardInputMixin.java/KeyboardInputMixin.md)                                        | Consumes the queue                                |
| [BuiltinAliasWithBooleanArgs](../../../alias/BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Boolean-arg alias type re-applied in `lockCursor` |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/LockAlias.md)                                          | Key lock mechanism                                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
