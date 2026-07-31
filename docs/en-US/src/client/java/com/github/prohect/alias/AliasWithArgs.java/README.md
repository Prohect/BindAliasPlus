# AliasWithArgs

Marker interface for builtin aliases that accept arguments. Provides `putToAliasesWithArgs` / `putToAliasesWithArgs_notSuggested` registration methods.

**Constraint**: Only builtin aliases implement this. User aliases always implement `AliasWithoutArgs`.

## Fields

_None._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | `default T putToAliasesWithArgs(String key)` | Register into `aliasesWithArgs` (suggested) |
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | `default T putToAliasesWithArgs_notSuggested(String key)` | Register into `aliasesWithArgs_notSuggested` (internal) |

## See Also

| Item | Description |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | The no-args counterpart |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Abstract base providing keyless overloads |
| [Alias](Alias.java/Alias.md) | Root interface declaring the registration maps |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
