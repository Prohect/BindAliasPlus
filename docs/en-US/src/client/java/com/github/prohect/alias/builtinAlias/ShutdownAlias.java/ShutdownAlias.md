# ShutdownAlias (src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ShutdownAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ShutdownAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that cleanly shuts down the game client by calling `Minecraft.getInstance().stop()`.

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithoutArgs()` into `Alias.aliasesWithoutArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Scheduling a stop triggers a graceful shutdown sequence.

Key collaborators: extends [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md). Uses `Minecraft.getInstance().stop()` to schedule a graceful stop. Logs the shutdown via `BindAliasClient.LOGGER`.

Primary use case: automated test workflows in the autoload CFG — define test aliases, run them, then call `shutdown` to exit cleanly without user intervention.

## See Also

| Item                                                                                     | Description                              |
| ---------------------------------------------------------------------------------------- | ---------------------------------------- |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class — no-arg alias base         |
| [BindAliasClient](../../../BindAliasClient.java/BindAliasClient.md)          | LOGGER used for shutdown message         |
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md)                               | Another no-arg utility alias             |
| [run](run.md)                                                                            | The `run` method that initiates shutdown |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
