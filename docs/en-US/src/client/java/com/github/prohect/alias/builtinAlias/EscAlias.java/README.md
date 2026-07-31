# EscAlias

Integer-arg alias for closing screens and toggling the pause menu. Supports close-only (`esc\0`) and toggle (`esc\1`) modes.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithIntegerArgs.flag`)_ | `int` | Inherited: 0 for close-only, 1 for toggle |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `EscAlias run(String args)` | Close current screen; optionally open pause menu in toggle mode |

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Base class for integer-arg aliases |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
