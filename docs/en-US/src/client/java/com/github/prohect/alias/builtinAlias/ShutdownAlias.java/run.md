# run method (src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java)

Logs a shutdown message and calls `MinecraftClient.getInstance().scheduleStop()()` to exit the game cleanly.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ShutdownAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Log `"[shutdown] Shutting down..."` at INFO level with tick prefix.
2. Call `MinecraftClient.getInstance().scheduleStop()` to schedule a graceful game stop. (Yarn: `scheduleStop()`; Mojang: `stop()`)

**Return value:** `this` (fluent return) — though the game may exit before the caller regains control.

**Side effects:** Schedules the game to stop. The game will close at the end of the current tick, allowing save operations and resource cleanup.

**No screen suppression:** Works on any screen.

**Safety:** Uses the clean `stop()` method, not a forced JVM exit.

## See Also

| Item | Description |
|------|-------------|
| [ShutdownAlias](ShutdownAlias.md) | Class overview |
| [ReloadCFGAlias](../ReloadCFGAlias.java/run.md) | Another system-level alias |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
