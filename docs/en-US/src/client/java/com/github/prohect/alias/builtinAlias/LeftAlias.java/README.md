# LeftAlias

Switch alias for the vanilla left strafe key (keyLeft / A key). Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+left`, false for `-left` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `LeftAlias run(String args)` | Press or release `options.keyLeft`; suppresses press on text-input screens only |

## See Also

| Item | Description |
|------|-------------|
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement |
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Mixin that reads movement key states |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
