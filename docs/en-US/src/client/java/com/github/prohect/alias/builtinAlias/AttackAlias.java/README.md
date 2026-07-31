# AttackAlias

Switch alias for the vanilla attack (left-click) key binding. Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+attack`, false for `-attack` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `AttackAlias run(String args)` | Press or release `options.keyAttack`; suppresses press on text-input screens |

## See Also

| Item | Description |
|------|-------------|
| [UseAlias](../UseAlias.java/UseAlias.md) | Right-click equivalent |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class: parseArgs, reapplyToGameKeyMapping |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Movement key switch alias |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
