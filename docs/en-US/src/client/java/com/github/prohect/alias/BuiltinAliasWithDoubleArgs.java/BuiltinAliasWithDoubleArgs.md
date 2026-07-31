# BuiltinAliasWithDoubleArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java)

## Syntax

```java
public abstract class com.github.prohect.alias.BuiltinAliasWithDoubleArgs<T extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<T>> extends com.github.prohect.alias.BuiltinAliasWithArgs<T>
```

## Static Initializer

_None._

## Remarks

Abstract base for built-in aliases that take a `double` value as their argument.

Parsing attempts variable resolution via `VarAlias.resolveDouble()` first,
then falls back to `Double.parseDouble()`. This allows the args string to be
a variable name (e.g., `$myValue`) instead of a literal number.

## See Also

| Item                                                                         | Description         |
| ---------------------------------------------------------------------------- | ------------------- |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class        |
| [parseArgs](parseArgs.md)                                                    | Parsing logic       |
| [VarAlias](builtinAlias/VarAlias.java/VarAlias.md)                           | Variable resolution |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
