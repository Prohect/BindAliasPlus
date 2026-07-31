# AliasWithArgs (src/client/java/com/github/prohect/alias/AliasWithArgs.java)

## Syntax

```java
public interface com.github.prohect.alias.AliasWithArgs<T extends com.github.prohect.alias.AliasWithArgs<T>> extends com.github.prohect.alias.Alias<T>
```

## Static Initializer

_None._

## Remarks

Sub-interface of `Alias` for aliases that accept arguments.

Only built-in aliases should implement this interface with valid argument handling.
User-defined aliases never implement `AliasWithArgs` — they implement `AliasWithoutArgs`
and delegate to registered aliases.

Provides two registration methods that insert `this` into the appropriate static
registry in `Alias`. The type parameter `T` enables fluent chaining.

## See Also

| Item                                                                         | Description                                     |
| ---------------------------------------------------------------------------- | ----------------------------------------------- |
| [Alias](../Alias.java/Alias.md)                                              | Parent interface                                |
| [AliasWithoutArgs](../AliasWithoutArgs.java/AliasWithoutArgs.md)             | Sibling interface for aliases without args      |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Abstract base class implementing this interface |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
