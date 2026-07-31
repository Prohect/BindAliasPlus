# handleWriteCFG method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleWriteCFG(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; reads `content` from the query string |

## Remarks

`POST /writeCFG?content=<cfg_content>` handler. On the main thread:

1. Extracts the `content` query parameter (the new CFG file content).
2. Writes the content to `config/bind-alias.cfg`, creating the directory if needed.
3. Triggers `reloadCFG` to reload aliases and variables from the updated file.
4. Returns `StateTracker.begin(false)` + `StateTracker.finish(begun)` with the state diff envelope.

If `content` is missing, returns an error envelope.

## See Also

| Item | Description |
|------|-------------|
| [handleReadCFG](handleReadCFG.md) | The read counterpart |
| [StateTracker](StateTracker.java/README.md) | State envelope production |
