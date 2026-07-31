# BindAlias

String-arg alias that sends `/bind` commands to the server to create key bindings for aliases.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none besides inherited)_ | | |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `BindAlias run(String args)` | Normalize args and send `/bind <aliasName> <key>` to server |

## See Also

| Item | Description |
|------|-------------|
| [AliasAlias](../AliasAlias.java/AliasAlias.md) | Sends `/alias` commands |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Base class for string-arg aliases |
| [BindAliasKeyBinding](../../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | Local key binding representation |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
