# FreeCursorAlias

Toggles a static flag that prevents Minecraft from grabbing the mouse cursor, allowing unrestricted cursor movement for a better dev/test experience.

**Base:** `BuiltinAliasWithBooleanArgs`

**Hidden from command suggestions** (registered via `putToAliasesWithArgs_notSuggested`).

## Fields

| Name | Type | Description |
|------|------|-------------|
| [freeCursor](freeCursor.md) | `static boolean` | Read by `MouseMixin.cancelGrabMouse()` — when true, mouse grab is cancelled |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String)` | Sets the `freeCursor` flag |

## See Also

| Item | Description |
|------|-------------|
| [MouseMixin](../../../mixin/client/MouseMixin.java/README.md) | Mixin that reads `freeCursor` to cancel `grabMouse()` |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/README.md) | Base class |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
