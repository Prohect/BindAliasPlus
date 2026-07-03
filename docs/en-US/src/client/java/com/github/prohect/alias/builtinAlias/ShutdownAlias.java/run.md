# run method (src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                      |
| ------ | -------- | ---------------------------------------------------------------- |
| `args` | `String` | Unused. ShutdownAlias takes no arguments; the string is ignored. |

## Remarks

Initiates a graceful game shutdown by calling `Minecraft.getInstance().stop()`. Logs the shutdown message via `BindAliasPlusClient.LOGGER.info`.

Algorithm:

1. Logs `"[shutdown] Shutting down..."` to the mod's logger.
2. Calls `Minecraft.getInstance().stop()` which schedules a clean stop on the next tick.

Side effects: initiates game shutdown; the client will exit after the current tick completes. This is a graceful stop, not a forced `System.exit()`.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"builtinShutdown"` matches an `AliasRecord`. Typically used at the end of an autoload CFG script.

Error handling: none. The `stop()` method is a fire-and-forget scheduling call.

## See Also

| Item                                                                      | Description                       |
| ------------------------------------------------------------------------- | --------------------------------- |
| [ShutdownAlias](ShutdownAlias.md)                                         | Owning class                      |
| [BindAliasPlusClient.LOGGER](../../../BindAliasPlusClient.java/LOGGER.md) | Logger used for shutdown message  |
| [ReloadCFGAlias.run](../ReloadCFGAlias.java/run.md)                       | Another no-arg utility run method |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
