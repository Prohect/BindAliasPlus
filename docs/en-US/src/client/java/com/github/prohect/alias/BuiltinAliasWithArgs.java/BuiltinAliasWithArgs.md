# BuiltinAliasWithArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public abstract class com.github.prohect.alias.BuiltinAliasWithArgs<T extends com.github.prohect.alias.BuiltinAliasWithArgs<T>> implements com.github.prohect.alias.AliasWithArgs<T>
```

## Static Initializer

_None._

## Remarks

Abstract base class for all built-in aliases that accept arguments.

Encapsulates the common pattern: a `builtinAliasName` field set at construction,
and overloaded `putToAliasesWithArgs()` / `putToAliasesWithArgs_notSuggested()`
methods that use the stored name as the registry key (unlike the interface
defaults which take an explicit `String` parameter).

Subclasses include:

- `BuiltinAliasWithBooleanArgs` — parses `"0"`/`"1"` into a boolean flag
- `BuiltinAliasWithDoubleArgs` — parses args into a `double` (with variable resolution)
- `BuiltinAliasWithIntegerArgs` — parses args into an `int` (with variable resolution)
- `BuiltinAliasWithGreedyStringArgs` — passes the full args string through

Instances are created once during mod initialization and remain for the lifetime
of the game. Not thread-safe.

## See Also

| Item                                                                                  | Description                                                      |
| ------------------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| [AliasWithArgs](../AliasWithArgs.java/AliasWithArgs.md)                               | Interface implemented                                            |
| [BuiltinAliasWithoutArgs](../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Sibling for aliases without args                                 |
| [UserAlias](../UserAlias.java/UserAlias.md)                                           | Checks `instanceof BuiltinAliasWithArgs` for screen blacklisting |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
