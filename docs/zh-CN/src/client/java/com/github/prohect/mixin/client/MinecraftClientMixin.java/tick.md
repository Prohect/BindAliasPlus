# tick 方法（src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "tick")
private void tick(CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入到 `MinecraftClient#tick()` 的 `HEAD`。每个客户端刻按顺序执行四个子系统：

1. **界面跟踪**：`BindAliasClient.currentScreen = McScreenHelper.getCurrentScreen(MinecraftClient.getInstance())`——更新全局当前界面引用以用于别名界面类型检查。

2. **WaitAlias 延迟任务**：从索引 0 到 `size` 遍历 `WaitAlias.tasksWaiting`。对索引 `i` 处的每个任务调用 `task.tick()`，任务完成（应移除）时返回 `1`，仍在等待时返回 `0`。已完成任务通过递减 `size` 压缩移除。使用带 `size` 调整的手动索引循环以正确处理移除。

3. **持续丢弃**：从 `Alias.aliasesWithArgs_notSuggested` 查找 `"builtinDrop"` 别名，转换为 `DropAlias`，并调用 `dropAlias.tickDrop()`。在按住丢弃别名时驱动容器界面和 3D 游戏中的每刻丢弃逻辑。

4. **MCP nap 倒计时**：调用 `McpHttpServer.tickNapTasks()`——递减 nap 任务计数器，归零时完成 futures。放在最后，使延迟的 envelope 捕获反映 WaitAlias 链和丢弃 tick 的所有状态变化。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAlias.tasksWaiting](../../../alias/builtinAlias/WaitAlias.java/tasksWaiting.md) | 待处理等待任务列表 |
| [DropAlias.tickDrop](../../../alias/builtinAlias/DropAlias.java/tickDrop.md) | 持续丢弃驱动器 |
| [McpHttpServer.tickNapTasks](../../../mcp/McpHttpServer.java/tickNapTasks.md) | nap 任务倒计时 |
| [McScreenHelper.getCurrentScreen](../../../util/McScreenHelper.java/getCurrentScreen.md) | 跨版本界面访问 |
