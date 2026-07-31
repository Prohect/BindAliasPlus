# ForwardAlias

Switch alias for the vanilla forward movement key (keyUp / W key). Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+forward`, false for `-forward` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `ForwardAlias run(String args)` | Press or release `options.keyUp`; suppresses press on text-input screens only |

## See Also

| Item | Description |
|------|-------------|
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | Left strafe |
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Mixin that reads movement key states |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
