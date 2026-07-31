# run method (src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java)

Logs a shutdown message and calls `Minecraft.stop()` to exit the game cleanly.

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
2. Call `Minecraft.getInstance().stop()` to schedule a graceful game stop.

**Return value:** `this` (fluent return) — though the game may exit before the caller regains control.

**Side effects:** Schedules the game to stop. The game will close at the end of the current tick, allowing save operations and resource cleanup.

**No screen suppression:** Works on any screen.

**Safety:** Uses the clean `stop()` method, not a forced JVM exit.

## See Also

| Item | Description |
|------|-------------|
| [ShutdownAlias](ShutdownAlias.md) | Class overview |
| [ReloadCFGAlias](../ReloadCFGAlias.java/run.md) | Another system-level alias |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
