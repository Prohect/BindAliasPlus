# putToAliasesWithArgs method (src/client/java/com/github/prohect/alias/AliasWithArgs.java)

## Syntax

```java
public default T putToAliasesWithArgs(java.lang.String)
```

## Parameters

| Name  | Type     | Description                                                                                                |
| ----- | -------- | ---------------------------------------------------------------------------------------------------------- |
| `key` | `String` | The alias name to register under. This is the string users type in the chat or config to invoke the alias. |

## Remarks

Registers this alias instance in `Alias.aliasesWithArgs` under the given key.

Aliases registered in this map are included in command completion suggestions
and are looked up last in the dispatch order (after both `aliasesWithoutArgs_*`
registries and `aliasesWithArgs_notSuggested`).

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                                      | Description                     |
| ------------------------------------------------------------------------- | ------------------------------- |
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | Registration without suggestion |
| [Alias.aliasesWithArgs](../Alias.java/aliasesWithArgs.md)                 | The target registry             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
