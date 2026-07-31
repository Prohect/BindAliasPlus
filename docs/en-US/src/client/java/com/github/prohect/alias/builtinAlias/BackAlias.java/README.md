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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
