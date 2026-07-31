# sendJson method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static void sendJson(HttpExchange exchange, int code, String json) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange to send the response on |
| `code` | `int` | HTTP status code (e.g., 200, 400) |
| `json` | `String` | The JSON response body |

## Remarks

Sends a JSON HTTP response. Sets `Content-Type: application/json`, writes the status code, and sends the response body. Used by all HTTP handlers for both success and error responses. Errors (IOExceptions during send) are logged.

## See Also

| Item | Description |
|------|-------------|
| [handleState](handleState.md) | Uses sendJson for all responses |
