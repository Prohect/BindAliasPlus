# sendJson 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
private static void sendJson(HttpExchange exchange, int code, String json) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `HttpExchange` | 发送响应的 HTTP exchange |
| `code` | `int` | HTTP 状态码（例如 200、400） |
| `json` | `String` | JSON 响应体 |

## 备注

发送 JSON HTTP 响应。设置 `Content-Type: application/json`、写入状态码并发送响应体。所有 HTTP 处理器的成功与错误响应都使用它。发送期间的错误（IOException）会被记录。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [handleState](handleState.md) | 所有响应均使用 sendJson |
