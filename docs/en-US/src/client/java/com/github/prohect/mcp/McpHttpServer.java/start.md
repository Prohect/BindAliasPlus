# start method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public static void start()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

Initializes and starts the MCP HTTP server on `127.0.0.1:25575`. Idempotent — if the server is already running (`server != null`), returns immediately without creating a duplicate.

**Setup**:
1. Creates an `HttpServer` bound to localhost only (not accessible from other machines).
2. Registers 6 context handlers: `/state`, `/screenshot`, `/runAlias`, `/defineAlias`, `/readCFG`, `/writeCFG`.
3. Configures a daemon `CachedThreadPool` executor — threads are named `BindAliasPlus-MCP` and won't prevent JVM shutdown.
4. Registers a shutdown hook to call `stop()` on JVM exit.
5. Logs startup confirmation.

Called by `BindAliasPlusClient.onInitializeClient()` during mod initialization. Exceptions during startup are caught and logged but don't crash the client.

## See Also

| Item | Description |
|------|-------------|
| [stop](stop.md) | Shuts down the server |
| [BindAliasPlusClient.onInitializeClient](../../BindAliasPlusClient.java/onInitializeClient.md) | Caller |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
