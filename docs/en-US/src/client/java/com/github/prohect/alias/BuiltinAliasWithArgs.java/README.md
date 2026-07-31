# BuiltinAliasWithArgs

Abstract base for all builtin aliases that accept arguments. Stores `builtinAliasName` and provides keyless registration overloads.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [builtinAliasName](builtinAliasName.md) | `@NotNull String` | The name used for registration and lookup |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | `T putToAliasesWithArgs()` | Register into `aliasesWithArgs` using `builtinAliasName` |
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | `T putToAliasesWithArgs_notSuggested()` | Register into `aliasesWithArgs_notSuggested` using `builtinAliasName` |

## See Also

| Item | Description |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | The interface this class implements |
| [BuiltinAliasWithBooleanArgs](BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Boolean subclass |
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Integer subclass |
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Double subclass |
| [BuiltinAliasWithStringArgs](BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | String subclass |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
