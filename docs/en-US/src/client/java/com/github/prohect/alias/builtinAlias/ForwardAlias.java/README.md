# ForwardAlias

Switch alias for the vanilla forward movement key (forwardKey / W key). Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+forward`, false for `-forward` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `ForwardAlias run(String args)` | Press or release `options.forwardKey`; suppresses press on text-input screens only |

## See Also

| Item | Description |
|------|-------------|
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | Left strafe |
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Mixin that reads movement key states |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
