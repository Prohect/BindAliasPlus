# AliasRecord

## Fields

| Name        | Type     | Description                                                                                                                                                     |
| ----------- | -------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `args`      | `String` | The arguments portion of the alias definition. For built-in aliases with args this is the parameter string; for aliases without args this is empty. Never null. |
| `aliasName` | `String` | The alias name (the key used to look up the alias in the registries). Never null.                                                                               |

## Methods

| Name        | Signature                | Description                                  |
| ----------- | ------------------------ | -------------------------------------------- |
| `args`      | `String args()`          | Record accessor for the arguments string.    |
| `aliasName` | `String aliasName()`     | Record accessor for the alias name.          |
| `equals`    | `boolean equals(Object)` | Auto-generated record equality comparison.   |
| `hashCode`  | `int hashCode()`         | Auto-generated record hash code.             |
| `toString`  | `String toString()`      | Auto-generated record string representation. |

## See Also

| Item                                        | Description                                                  |
| ------------------------------------------- | ------------------------------------------------------------ |
| [Alias](../Alias.java/Alias.md)             | Core interface whose registries are looked up by `aliasName` |
| [UserAlias](../UserAlias.java/UserAlias.md) | Builds queues of `AliasRecord` via `decodeArgs2Alias()`      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
