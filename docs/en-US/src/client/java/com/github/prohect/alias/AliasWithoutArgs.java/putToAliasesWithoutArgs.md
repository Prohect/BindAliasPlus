# putToAliasesWithoutArgs method (src/client/java/com/github/prohect/alias/AliasWithoutArgs.java)

## Syntax

```java
public default T putToAliasesWithoutArgs(java.lang.String)
```

## Parameters

| Name  | Type     | Description                                                                          |
| ----- | -------- | ------------------------------------------------------------------------------------ |
| `key` | `String` | The alias name to register under. This is the string users type to invoke the alias. |

## Remarks

Registers this alias instance in `Alias.aliasesWithoutArgs` under the given key.

Aliases registered in this map are included in command completion suggestions
and are looked up first in the dispatch order (before all other registries).

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                                            | Description                     |
| ------------------------------------------------------------------------------- | ------------------------------- |
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | Registration without suggestion |
| [Alias.aliasesWithoutArgs](../Alias.java/aliasesWithoutArgs.md)                 | The target registry             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
