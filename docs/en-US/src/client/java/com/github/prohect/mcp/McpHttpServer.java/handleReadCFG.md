# handleReadCFG method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleReadCFG(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; response body receives the `content` field with raw CFG text |

## Remarks

`GET /readCFG` handler. Reads the CFG file from the game directory (`config/bind-alias.cfg`) and returns its contents as a JSON envelope with a `"content"` string member. If the file does not exist, returns an empty string. Does not require the main thread (file I/O only).

## See Also

| Item | Description |
|------|-------------|
| [handleWriteCFG](handleWriteCFG.md) | The write counterpart |
