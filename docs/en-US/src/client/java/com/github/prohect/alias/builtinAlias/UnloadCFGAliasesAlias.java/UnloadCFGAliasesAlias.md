# UnloadCFGAliasesAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to remove all user aliases that were loaded from the config file. Registered as `unloadCFGAliases`.

**Purpose**: Selectively removes only aliases with `fromAutoload=true` (created during `loadCFG()`), leaving runtime-created aliases (via `/alias` command) intact.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Modifies `Alias.aliasesWithoutArgs` directly.

## See Also

| Item                                                                                     | Description                        |
| ---------------------------------------------------------------------------------------- | ---------------------------------- |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md)                      | Calls this as part of "unload all" |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class                       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
