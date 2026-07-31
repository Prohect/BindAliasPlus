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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
