# BuiltinAliasWithBooleanArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public abstract class com.github.prohect.alias.BuiltinAliasWithBooleanArgs<T extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<T>> extends com.github.prohect.alias.BuiltinAliasWithArgs<T>
```

## Static Initializer

_None._

## Remarks

Abstract base for built-in aliases that take a boolean flag as their argument.

Parses `"0"` as `false` (key-up / release) and `"1"` as `true` (key-down / press).
Invalid arguments log a warning but leave `flag` at `false`.

The `flag` field is public so that it can be read externally after parsing.

Subclasses should consider whether they need to cancel the press event when a
text-input screen is open — the `reapplyToGameKeyMapping()` method helps restore
the key state when returning from such screens.

## See Also

| Item                                                                         | Description            |
| ---------------------------------------------------------------------------- | ---------------------- |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class           |
| [parseArgs](parseArgs.md)                                                    | Argument parsing logic |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md)                        | Key-state restoration  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
