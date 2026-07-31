# AliasWithoutArgs

Marker interface for aliases triggered by name only (no arguments). Provides `putToAliasesWithoutArgs` / `putToAliasesWithoutArgs_notSuggested` registration methods.

All user-defined aliases (`UserAlias`) implement this interface, as do several builtin single-action aliases.

## Fields

_None._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | `default T putToAliasesWithoutArgs(String key)` | Register into `aliasesWithoutArgs` (suggested) |
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | `default T putToAliasesWithoutArgs_notSuggested(String key)` | Register into `aliasesWithoutArgs_notSuggested` (internal) |

## See Also

| Item | Description |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | The with-args counterpart |
| [BuiltinAliasWithoutArgs](BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Abstract base providing keyless overloads |
| [UserAlias](UserAlias.java/UserAlias.md) | The only non-builtin AliasWithoutArgs |
| [Alias](Alias.java/Alias.md) | Root interface declaring the registration maps |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
