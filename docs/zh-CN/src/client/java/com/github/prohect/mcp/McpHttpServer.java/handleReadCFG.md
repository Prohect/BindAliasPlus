# handleReadCFG 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static void handleReadCFG(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；响应体以 `content` 字段接收原始 CFG 文本 |

## 备注

`GET /readCFG` 处理器。从游戏目录（`config/bind-alias.cfg`）读取 CFG 文件，以带 `"content"` 字符串成员的 JSON envelope 返回其内容。文件不存在时返回空字符串。不需要主线程（仅文件 I/O）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [handleWriteCFG](handleWriteCFG.md) | 对应的写入处理器 |
