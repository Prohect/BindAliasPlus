# handleRunAlias 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static void handleRunAlias(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；从查询字符串读取 `def` 与可选的 `nap` |

## 备注

`POST /runAlias?def=<alias_chain>&nap=<client_ticks>` 处理器。在主线程上：

1. 提取 `def`（别名链定义）与可选的 `nap` 查询参数。
2. 调用 `StateTracker.begin(false)` 在别名执行**之前**对状态做快照。
3. 解析别名链定义，通过别名系统依次执行每个别名。
4. 若提供了 `nap` 且 > 0：
   - 创建 `ticksLeft = nap` 的 `NapTask`，其 `CompletableFuture<String>` 预置开始时的 envelope 字符串。
   - 把任务加入 `NAP_TASKS`。
   - HTTP 响应被延迟——该 `CompletableFuture` 将在指定客户端刻延迟后由 `tickNapTasks()` 完成，届时 `StateTracker.finish()` 捕获 nap 后的状态。
5. 若无 `nap`（或 `nap` = 0）：
   - 立即调用 `StateTracker.finish(begun)` 并返回 envelope。

`nap` 参数被限制到 `MAX_NAP_TICKS`（600，约 30 秒），防止过长的延迟响应。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | 执行前的状态快照 |
| [StateTracker.finish](StateTracker.java/finish.md) | 执行后的状态捕获 |
| [tickNapTasks](tickNapTasks.md) | nap 任务倒计时 |
| [Alias.run](../../alias/Alias.java/run.md) | 别名执行系统 |
