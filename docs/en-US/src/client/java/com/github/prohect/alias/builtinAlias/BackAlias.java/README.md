# BackAlias

Switch alias for the vanilla backward movement key (keyDown / S key). Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+back`, false for `-back` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `BackAlias run(String args)` | Press or release `options.keyDown`; suppresses press on text-input screens only |

## See Also

| Item | Description |
|------|-------------|
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement equivalent |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | Left strafe equivalent |
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe equivalent |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Mixin that reads movement key states |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
