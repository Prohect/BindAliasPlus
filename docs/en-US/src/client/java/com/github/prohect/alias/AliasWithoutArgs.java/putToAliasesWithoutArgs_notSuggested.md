# putToAliasesWithoutArgs_notSuggested method (src/client/java/com/github/prohect/alias/AliasWithoutArgs.java)

## Syntax

```java
public default T putToAliasesWithoutArgs_notSuggested(java.lang.String)
```

## Parameters

| Name  | Type     | Description                       |
| ----- | -------- | --------------------------------- |
| `key` | `String` | The alias name to register under. |

## Remarks

Registers this alias instance in `Alias.aliasesWithoutArgs_notSuggested` under the given key.

Aliases registered in this map are hidden from command completion suggestions
but are still dispatchable. They are looked up second in the dispatch order
(after the suggested `aliasesWithoutArgs` but before any `aliasesWithArgs_*`
registries).

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                                                      | Description                  |
| ----------------------------------------------------------------------------------------- | ---------------------------- |
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md)                                     | Registration with suggestion |
| [Alias.aliasesWithoutArgs_notSuggested](../Alias.java/aliasesWithoutArgs_notSuggested.md) | The target registry          |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
