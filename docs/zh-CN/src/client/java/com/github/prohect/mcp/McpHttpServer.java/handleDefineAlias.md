# handleDefineAlias 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static void handleDefineAlias(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；从查询字符串读取 `name` 与 `def` |

## 备注

`POST /defineAlias?name=<name>&def=<definition>` 处理器。在主线程上：

1. 提取 `name` 与 `def` 查询参数。
2. 校验别名名：必须是单个单词（不含空格、`\`、`;` 或 `/`），不能为空。跳过已被 `+attack`、`slot` 等内置别名占用的名称。
3. 通过模组的别名系统定义别名。
4. 返回 `StateTracker.begin(false)` + `StateTracker.finish(begun)`，带状态差分 envelope。

错误（缺少参数、名称非法）返回带 `"error"` 成员的 envelope。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | 状态 envelope 产出 |
