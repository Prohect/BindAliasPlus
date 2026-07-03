# run method (src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                      |
| ------ | -------- | ---------------------------------------------------------------- |
| `args` | `String` | The message to log at INFO level. Passed directly to the logger. |

## Remarks

Logs the args string to the mod's logger at INFO level, useful for debugging alias chains.

Algorithm:

1. Calls `BindAliasPlusClient.LOGGER.info(args)` passing the full args string.

Side effects: writes a line to the game log output.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"log"` matches an AliasRecord. Also called from `AliasAlias` and other internal aliases for logging.

Error handling: none. Any string (including null) is passed directly to the logger (null-safe by SLF4J convention).

## See Also

| Item                                                                      | Description                       |
| ------------------------------------------------------------------------- | --------------------------------- |
| [LogAlias](LogAlias.md)                                                   | Owning class                      |
| [BindAliasPlusClient.LOGGER](../../../BindAliasPlusClient.java/LOGGER.md) | The logger this method writes to  |
| [LocalSayAlias.run](../LocalSayAlias.java/run.md)                         | Displays messages in chat instead |
| [SayAlias.run](../SayAlias.java/run.md)                                   | Sends chat to server              |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
