# BuiltinAliasWithoutArgs

Abstract base for builtin aliases that take no arguments. Stores `builtinAliasName` and provides keyless registration overloads. All key-event-triggerable builtin aliases (esc, toggleInventory, swapHand, pickItem, etc.) extend this.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [builtinAliasName](builtinAliasName.md) | `@NotNull String` | The name used for registration and lookup |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | `T putToAliasesWithoutArgs()` | Register into `aliasesWithoutArgs` using `builtinAliasName` |
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | `T putToAliasesWithoutArgs_notSuggested()` | Register into `aliasesWithoutArgs_notSuggested` using `builtinAliasName` |

## See Also

| Item | Description |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | The interface this class implements |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | The with-args counterpart |
| [UserAlias](UserAlias.java/UserAlias.md) | Non-builtin AliasWithoutArgs — does NOT extend this class |
| [builtinAlias](builtinAlias/README.md) | Concrete implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
