# BuiltinAliasWithArgs

## Fields

| Name               | Type     | Description                                                                   |
| ------------------ | -------- | ----------------------------------------------------------------------------- |
| `builtinAliasName` | `String` | The alias name set at construction. Used as the registry key and for logging. |

## Methods

| Name                                | Signature                               | Description                                                                                       |
| ----------------------------------- | --------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `putToAliasesWithArgs`              | `T putToAliasesWithArgs()`              | Registers this alias in `Alias.aliasesWithArgs` using `builtinAliasName` as the key.              |
| `putToAliasesWithArgs_notSuggested` | `T putToAliasesWithArgs_notSuggested()` | Registers this alias in `Alias.aliasesWithArgs_notSuggested` using `builtinAliasName` as the key. |

## See Also

| Item                                                                                                             | Description                            |
| ---------------------------------------------------------------------------------------------------------------- | -------------------------------------- |
| [AliasWithArgs](../AliasWithArgs.java/AliasWithArgs.md)                                                          | Interface this class implements        |
| [BuiltinAliasWithoutArgs](../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md)                            | Sibling class for aliases without args |
| [BuiltinAliasWithBooleanArgs](../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md)                | Subclass for boolean-flag aliases      |
| [BuiltinAliasWithDoubleArgs](../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md)                   | Subclass for double-flag aliases       |
| [BuiltinAliasWithIntegerArgs](../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md)                | Subclass for integer-flag aliases      |
| [BuiltinAliasWithGreedyStringArgs](../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Subclass for greedy string aliases     |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
