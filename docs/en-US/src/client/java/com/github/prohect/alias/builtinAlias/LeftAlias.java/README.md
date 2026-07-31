# LeftAlias

Switch alias for the vanilla left strafe key (leftKey / A key). Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+left`, false for `-left` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `LeftAlias run(String args)` | Press or release `options.leftKey`; suppresses press on text-input screens only |

## See Also

| Item | Description |
|------|-------------|
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement |
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Mixin that reads movement key states |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
