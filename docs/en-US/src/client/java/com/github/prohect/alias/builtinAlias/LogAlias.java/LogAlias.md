# LogAlias (src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java)

Builtin alias that writes a message to the mod log file. Extends `BuiltinAliasWithArgs` (accepts raw string args).

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LogAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LogAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"log"`. Usage: `log\<text>`.

Writes the message to the mod log at INFO level via `BindAliasClient.LOGGER.info()`. The message is prefixed with `BindAliasClient.tickPrefix()` (the current client tick number) for timestamp identification.

This is the primary debugging output channel for alias scripts — unlike `localSay`, log output does not appear in the game chat and persists in the log file for later inspection.

## See Also

| Item | Description |
|------|-------------|
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md) | Displays message in local chat |
| [SayAlias](../SayAlias.java/SayAlias.md) | Sends message to server chat |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Base class for general-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
