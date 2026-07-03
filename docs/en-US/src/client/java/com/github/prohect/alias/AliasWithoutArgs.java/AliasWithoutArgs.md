# AliasWithoutArgs (src/client/java/com/github/prohect/alias/AliasWithoutArgs.java)

## Syntax

```java
public interface com.github.prohect.alias.AliasWithoutArgs<T extends com.github.prohect.alias.AliasWithoutArgs<T>> extends com.github.prohect.alias.Alias<T>
```

## Static Initializer

_None._

## Remarks

Sub-interface of `Alias` for aliases that do not accept arguments.

Both built-in aliases without args (`BuiltinAliasWithoutArgs`) and user-defined
aliases (`UserAlias`) implement this interface.

Provides two registration methods that insert `this` into the appropriate static
registry in `Alias`. The type parameter `T` enables fluent chaining.

## See Also

| Item                                                                                  | Description                                          |
| ------------------------------------------------------------------------------------- | ---------------------------------------------------- |
| [Alias](../Alias.java/Alias.md)                                                       | Parent interface                                     |
| [AliasWithArgs](../AliasWithArgs.java/AliasWithArgs.md)                               | Sibling interface for aliases with args              |
| [BuiltinAliasWithoutArgs](../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Abstract base class implementing this interface      |
| [UserAlias](../UserAlias.java/UserAlias.md)                                           | User-defined alias chain implementing this interface |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
