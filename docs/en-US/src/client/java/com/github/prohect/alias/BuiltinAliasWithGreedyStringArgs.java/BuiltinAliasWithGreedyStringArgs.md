# BuiltinAliasWithGreedyStringArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithGreedyStringArgs.java)

## Syntax

```java
public abstract class com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<T extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<T>> extends com.github.prohect.alias.BuiltinAliasWithArgs<T>
```

## Static Initializer

_None._

## Remarks

Abstract base for built-in aliases that consume the entire argument string as-is.

Unlike normal aliases whose definitions are divided by `Alias.divider4AliasDefinition`
(space), this class defines its own `divider4AliasDefinition` (`';'`) so that its
args can contain spaces and nested alias definitions without being split
prematurely.

The `divider4AliasDefinition` field in this class shadows `Alias.divider4AliasDefinition`
for any code that references it through this type.

## See Also

| Item                                                                         | Description                                |
| ---------------------------------------------------------------------------- | ------------------------------------------ |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                               |
| [divider4AliasDefinition](divider4AliasDefinition.md)                        | The alternative divider used by this class |
| [Alias.divider4AliasDefinition](../Alias.java/divider4AliasDefinition.md)    | The default divider                        |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
