# sendJson method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static void sendJson(HttpExchange exchange, int code, String json) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange to respond to. |
| `code` | `int` | HTTP status code (200, 400, 500). |
| `json` | `String` | JSON response body. Must already be properly escaped. |

## Remarks

Sends a JSON HTTP response. Sets `Content-Type: application/json; charset=utf-8`, writes the status code, and sends the JSON bytes as UTF-8.

Called by all endpoint handlers to return results. The `json` parameter is expected to be already escaped — handlers compose JSON strings manually using `jsonEscape()` rather than a JSON library.

## See Also

| Item | Description |
|------|-------------|
| [jsonEscape](jsonEscape.md) | Escapes strings before embedding in JSON |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
