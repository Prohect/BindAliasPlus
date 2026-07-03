# putToAliasesWithArgs_notSuggested method (src/client/java/com/github/prohect/alias/AliasWithArgs.java)

## Syntax

```java
public default T putToAliasesWithArgs_notSuggested(java.lang.String)
```

## Parameters

| Name  | Type     | Description                       |
| ----- | -------- | --------------------------------- |
| `key` | `String` | The alias name to register under. |

## Remarks

Registers this alias instance in `Alias.aliasesWithArgs_notSuggested` under the given key.

Aliases registered in this map are hidden from command completion suggestions
but are still dispatchable. They are looked up third in the dispatch order
(after both `aliasesWithoutArgs_*` registries but before the suggested
`aliasesWithArgs`).

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                                                | Description                  |
| ----------------------------------------------------------------------------------- | ---------------------------- |
| [putToAliasesWithArgs](putToAliasesWithArgs.md)                                     | Registration with suggestion |
| [Alias.aliasesWithArgs_notSuggested](../Alias.java/aliasesWithArgs_notSuggested.md) | The target registry          |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
