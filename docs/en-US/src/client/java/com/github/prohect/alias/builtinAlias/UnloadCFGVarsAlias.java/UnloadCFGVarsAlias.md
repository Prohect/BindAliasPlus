# UnloadCFGVarsAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to remove all variables that were loaded from the config file. Registered as `unloadCFGVars`.

**Purpose**: Selectively removes only variables tracked in `VarAlias.AUTOLOADED_VARIABLES`, leaving runtime-created variables (via `/var` command) intact. Removes each variable from both `VarAlias.VARIABLES` and `VarAlias.AUTOLOADED_VARIABLES`.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Modifies `VarAlias.VARIABLES` and `VarAlias.AUTOLOADED_VARIABLES`.

## See Also

| Item                                                                                     | Description                        |
| ---------------------------------------------------------------------------------------- | ---------------------------------- |
| [VarAlias.AUTOLOADED_VARIABLES](../VarAlias.java/AUTOLOADED_VARIABLES.md)                | The tracking set consumed here     |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md)                      | Calls this as part of "unload all" |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class                       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
