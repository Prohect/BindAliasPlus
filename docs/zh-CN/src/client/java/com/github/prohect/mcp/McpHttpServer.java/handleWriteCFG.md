# handleWriteCFG 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static void handleWriteCFG(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；从查询字符串读取 `content` |

## 备注

`POST /writeCFG?content=<cfg_content>` 处理器。在主线程上：

1. 提取 `content` 查询参数（新的 CFG 文件内容）。
2. 把内容写入 `config/bind-alias.cfg`，必要时创建目录。
3. 触发 `reloadCFG`，从更新后的文件重新加载别名与变量。
4. 返回 `StateTracker.begin(false)` + `StateTracker.finish(begun)`，带状态差分 envelope。

`content` 缺失时返回错误 envelope。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [handleReadCFG](handleReadCFG.md) | 对应的读取处理器 |
| [StateTracker](StateTracker.java/README.md) | 状态 envelope 产出 |
