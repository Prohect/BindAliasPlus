# putToAliasesWithArgs_notSuggested method (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public T putToAliasesWithArgs_notSuggested()
```

## Remarks

Registers this alias in `Alias.aliasesWithArgs_notSuggested` using `builtinAliasName` as the key.

Unlike the interface default `AliasWithArgs.putToAliasesWithArgs_notSuggested(String)`,
this no-arg overload uses the stored `builtinAliasName` field.

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                                                | Description                   |
| ----------------------------------------------------------------------------------- | ----------------------------- |
| [builtinAliasName](builtinAliasName.md)                                             | The key used for registration |
| [Alias.aliasesWithArgs_notSuggested](../Alias.java/aliasesWithArgs_notSuggested.md) | The target registry           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
