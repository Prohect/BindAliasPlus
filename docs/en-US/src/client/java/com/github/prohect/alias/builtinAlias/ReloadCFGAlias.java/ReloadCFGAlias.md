# ReloadCFGAlias (src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ReloadCFGAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ReloadCFGAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that reloads the mod configuration from `BindAliasPlusClient.cfgPath` by calling `loadCFG()`.

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithoutArgs()` into `Alias.aliasesWithoutArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. The `loadCFG()` call reads the config file, parses aliases, binds, and vars, which may take non-trivial time on large configs.

Key collaborators: extends [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md). Delegates all work to `BindAliasPlusClient.INSTANCE.loadCFG()`, which clears existing state and re-parses the config file at `run/config/bind-alias-plus.cfg`.

## See Also

| Item                                                                                     | Description                                 |
| ---------------------------------------------------------------------------------------- | ------------------------------------------- |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class — no-arg alias base            |
| [BindAliasPlusClient.loadCFG](../../../BindAliasPlusClient.java/loadCFG.md)              | The method that actually reloads the config |
| [ShutdownAlias](../ShutdownAlias.java/ShutdownAlias.md)                                  | Another no-arg utility alias                |
| [run](run.md)                                                                            | The `run` method that reloads config        |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
