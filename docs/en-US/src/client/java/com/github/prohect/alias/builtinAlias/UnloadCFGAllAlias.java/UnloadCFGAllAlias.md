# UnloadCFGAllAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias>
```

## Static Initializer

_None._

## Remarks

Convenience builtin alias that removes all config-loaded aliases, keybindings, and variables in one call. Registered as `unloadCFGAll`.

**Purpose**: Combines `unloadCFGAliases`, `unloadCFGBinds`, and `unloadCFGVars` into a single command. Temporarily enables silent mode to suppress per-operation log spam, then logs a single summary line with counts.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup. Creates temporary instances of the three sub-aliases for each call.

**Thread safety**: Not thread-safe (render-thread only).

**Key collaborators**: Delegates to [UnloadCFGAliasesAlias](UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md), [UnloadCFGBindsAlias](UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md), and [UnloadCFGVarsAlias](UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md). Uses `BindAliasPlusClient.silentMode` to suppress intermediate logging.

## See Also

| Item                                                                                     | Description                    |
| ---------------------------------------------------------------------------------------- | ------------------------------ |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md)          | Removes autoloaded aliases     |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md)                | Removes autoloaded keybindings |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md)                   | Removes autoloaded variables   |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class                   |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
