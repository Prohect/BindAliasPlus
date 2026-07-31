# start method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public static void start()
```

## Remarks

Starts the HTTP server on a daemon thread. Port selection:

1. Reads the `"bind-alias.mcp.port"` system property, defaulting to `8095`.
2. Attempts to bind to that port; if occupied, increments and retries up to `MAX_PORT_ATTEMPTS` (10) times.
3. On success, stores the actual port in the static `port` field and logs it.
4. Registers all endpoint handlers and starts the executor.

The server is configured with a read timeout (`TIMEOUT_SECONDS` = 120) to prevent hung connections. Called from `BindAliasClient.onInitializeClient()` during client mod initialization.

## See Also

| Item | Description |
|------|-------------|
| [stop](stop.md) | Server shutdown |
| [port](port.md) | Returns the actual bound port |
