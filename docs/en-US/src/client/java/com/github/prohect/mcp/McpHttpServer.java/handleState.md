# handleState method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleState(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; response body receives the full game-state JSON envelope |

## Remarks

`GET /state` handler. On the main thread, calls `StateTracker.begin(true)` to force a full snapshot (all state members included) and `StateTracker.finish(begun)` to drain channels. Returns the resulting JSON envelope with HTTP 200. The `full = true` parameter ensures every state member is included even if unchanged since the previous snapshot.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | Begins the envelope (full mode) |
| [StateTracker.finish](StateTracker.java/finish.md) | Finishes and drains channels |
