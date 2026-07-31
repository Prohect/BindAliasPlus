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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
