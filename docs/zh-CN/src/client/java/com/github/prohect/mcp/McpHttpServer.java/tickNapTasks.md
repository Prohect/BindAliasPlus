# tickNapTasks 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
public static void tickNapTasks()
```

## 备注

每个客户端刻由 `MinecraftClientMixin.tick()` 调用。遍历 `NAP_TASKS` 列表，递减每个任务的 `ticksLeft` 计数器。计数器归零时，用最终 envelope（对先前已开始的 envelope 调用 `StateTracker.finish` 捕获）完成该任务的 `CompletableFuture<String>`。已取消的任务（`stop()` 期间设置标志）也会被完成（带取消响应）。

安全超时防止挂起：若任一 nap 任务等待超过 `NAP_TIMEOUT_MARGIN_MS`（墙钟 5 分钟），则被强制完成。`ticksLeft` 归零的任务在完成时从列表中移除。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | 每个客户端刻的调用方 |
| [handleRunAlias](handleRunAlias.md) | 提供 `nap` 参数时创建 nap 任务 |
| [StateTracker.finish](StateTracker.java/finish.md) | 为完成的 nap 任务捕获最终 envelope |
