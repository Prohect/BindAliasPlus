# BuiltinAliasWithoutArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public abstract class com.github.prohect.alias.BuiltinAliasWithoutArgs<T extends com.github.prohect.alias.BuiltinAliasWithoutArgs<T>> implements com.github.prohect.alias.AliasWithoutArgs<T>
```

## Static Initializer

_None._

## Remarks

Abstract base class for all built-in aliases that do not accept arguments.

Encapsulates the common pattern: a `builtinAliasName` field set at construction,
and overloaded `putToAliasesWithoutArgs()` / `putToAliasesWithoutArgs_notSuggested()`
methods that use the stored name as the registry key (unlike the interface
defaults which take an explicit `String` parameter).

Used for aliases like toggle actions, camera switches, and other single-action
built-in behaviors that don't need parameter parsing.

Instances are created once during mod initialization and remain for the lifetime
of the game. Not thread-safe.

## See Also

| Item                                                                         | Description                                                 |
| ---------------------------------------------------------------------------- | ----------------------------------------------------------- |
| [AliasWithoutArgs](../AliasWithoutArgs.java/AliasWithoutArgs.md)             | Interface implemented                                       |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Sibling for aliases with args                               |
| [UserAlias](../UserAlias.java/UserAlias.md)                                  | User-defined alias implementing `AliasWithoutArgs` directly |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
