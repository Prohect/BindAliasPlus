# BuiltinAliasWithDoubleArgs

Abstract base for builtin aliases whose single arg is a `double`. Used by `setYaw` and `setPitch`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [flag](flag.md) | `double` | The parsed double value (default `0.0`) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [parseArgs](parseArgs.md) | `void parseArgs(String args)` | Resolve args via variable lookup or `Double.parseDouble` |

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Integer counterpart |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | Variable resolution used by parseArgs |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
