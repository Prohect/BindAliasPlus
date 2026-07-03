# UnloadCFGBindsAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to remove all keybindings that were loaded from the config file. Registered as `unloadCFGBinds`.

**Purpose**: Selectively removes only bindings with `fromAutoload=true` from `BindAliasPlusClient.BINDING_PLUS`. Also cleans up associated aliases from `Alias.aliasesWithoutArgs_fromBindCommand`. Runtime-created bindings (via `/bind` or `/bindByAliasName`) are not affected.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

## See Also

| Item                                                                                     | Description                        |
| ---------------------------------------------------------------------------------------- | ---------------------------------- |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md)                      | Calls this as part of "unload all" |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class                       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
