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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
