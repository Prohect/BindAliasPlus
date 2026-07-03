# putToAliasesWithArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public T putToAliasesWithArgs()
```

## Remarks

Registers this alias in `Alias.aliasesWithArgs` using `builtinAliasName` as the key.

Unlike the interface default `AliasWithArgs.putToAliasesWithArgs(String)`, this
no-arg overload uses the stored `builtinAliasName` field, so subclasses do not
need to repeat the name.

Returns `this` cast to `T` for fluent chaining during registration.

## Return value

This alias instance, cast to `T`.

## See Also

| Item                                                      | Description                   |
| --------------------------------------------------------- | ----------------------------- |
| [builtinAliasName](builtinAliasName.md)                   | The key used for registration |
| [Alias.aliasesWithArgs](../Alias.java/aliasesWithArgs.md) | The target registry           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
