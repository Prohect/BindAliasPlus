# stop method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public static void stop()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

Stops the MCP HTTP server with a 0-second grace period — all active connections are immediately terminated. Sets `server = null` so a subsequent `start()` call can create a fresh instance.

Logs a stop confirmation message. Called by the JVM shutdown hook registered in `start()`, and also callable programmatically via the `/alias shutdownMCP` command (builtin `ShutdownAlias`).

## See Also

| Item | Description |
|------|-------------|
| [start](start.md) | Creates and starts the server |
| [ShutdownAlias](../../alias/builtinAlias/ShutdownAlias.java/ShutdownAlias.md) | Builtin alias that calls `stop()` |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAliasPlus/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
