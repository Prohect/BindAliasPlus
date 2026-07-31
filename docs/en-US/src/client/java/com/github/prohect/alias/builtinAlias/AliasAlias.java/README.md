# AliasAlias

String-arg alias that sends `/alias` commands to the server to define or redefine user aliases at runtime.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none besides inherited)_ | | |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `AliasAlias run(String args)` | Normalize args and send `/alias <name> <definition>` to server |

## See Also

| Item | Description |
|------|-------------|
| [BindAlias](../BindAlias.java/BindAlias.md) | Sends `/bind` commands to server |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Base class for string-arg aliases |
| [UserAlias](../../UserAlias.java/UserAlias.md) | Local representation of user aliases |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
