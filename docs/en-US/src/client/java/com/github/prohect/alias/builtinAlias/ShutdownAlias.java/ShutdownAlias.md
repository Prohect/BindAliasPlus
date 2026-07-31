# ShutdownAlias (src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java)

One-shot alias that cleanly shuts down the game. Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ShutdownAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ShutdownAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinShutdown` (internal, exposed as `builtinShutdown`).

**Behavior:** Logs a shutdown message and calls `Minecraft.getInstance().stop()` to schedule a graceful game stop. The game closes after the current tick completes.

**Use case:** Designed for automated test workflows using the CFG autoload feature — define test aliases in the config, run them, then call `builtinShutdown` at the end to cleanly exit. Also useful for MCP agents to terminate the game session.

**No screen suppression:** Works on any screen (it's a system operation, not a game input).

**Requirements:** None — works even if player is null.

**Safety:** This is a clean shutdown (`stop()`), not a forced exit (`System.exit()`). It lets the game save state and close resources properly.

## See Also

| Item | Description |
|------|-------------|
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md) | Reload config (another system-level alias) |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Base class for one-shot aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
