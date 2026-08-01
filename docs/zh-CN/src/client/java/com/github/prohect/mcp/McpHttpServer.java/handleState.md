# handleState 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static void handleState(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；响应体接收完整的游戏状态 JSON envelope |

## 备注

`GET /state` 处理器。在主线程上，调用 `StateTracker.begin(true)` 强制完整快照（包含所有状态成员），再调用 `StateTracker.finish(begun)` 排空 channel。以 HTTP 200 返回生成的 JSON envelope。`full = true` 参数确保即使自上次快照以来未变化，每个状态成员也都会包含。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | 开始 envelope（完整模式） |
| [StateTracker.finish](StateTracker.java/finish.md) | 结束并排空 channel |
