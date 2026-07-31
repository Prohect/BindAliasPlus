# BuiltinAliasWithIntegerArgs

Abstract base for builtin aliases whose single arg is an `int`. Used by `slot`, `wait`, `yaw`, and `pitch`.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [flag](flag.md) | `int` | The parsed integer value (default `0`) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [parseArgs](parseArgs.md) | `void parseArgs(String args)` | Resolve args via variable lookup or `Integer.parseInt` |

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Double counterpart |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class |
| [VarAlias.resolveInt](builtinAlias/VarAlias.java/resolveInt.md) | Variable resolution used by parseArgs |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
