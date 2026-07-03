# putToAliasesWithoutArgs_notSuggested method (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public T putToAliasesWithoutArgs_notSuggested()
```

## Remarks

Registers this alias in `Alias.aliasesWithoutArgs_notSuggested` using `builtinAliasName` as the key.

Unlike the interface default `AliasWithoutArgs.putToAliasesWithoutArgs_notSuggested(String)`,
this no-arg overload uses the stored `builtinAliasName` field.

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                                                      | Description                   |
| ----------------------------------------------------------------------------------------- | ----------------------------- |
| [builtinAliasName](builtinAliasName.md)                                                   | The key used for registration |
| [Alias.aliasesWithoutArgs_notSuggested](../Alias.java/aliasesWithoutArgs_notSuggested.md) | The target registry           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
