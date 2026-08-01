# MinecraftClientMixin（src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java）

## 语法

```java
@Mixin(value = MinecraftClient.class)
public class com.github.prohect.mixin.client.MinecraftClientMixin
```

## 静态初始化器

_无。_

## 备注

在 `tick()` 处混入 `net.minecraft.client.MinecraftClient`。这是每个客户端刻的中央集成点，按明确定义的顺序驱动多个子系统：

1. **界面跟踪**：通过 [`McScreenHelper.getCurrentScreen()`](../../../util/McScreenHelper.java/getCurrentScreen.md) 更新 `BindAliasClient.currentScreen`，为所有别名的界面类型检查提供跨版本界面访问。
2. **WaitAlias 计时器**：遍历 `WaitAlias.tasksWaiting`，对每个延迟任务调用 `tick()`，并将已完成的任务从列表中压缩移除。
3. **持续丢弃**：驱动 `DropAlias.tickDrop()`——在按住 `+drop` 别名时处理每刻丢弃逻辑，覆盖容器界面（通过 `onMouseClick`）和 3D 游戏视图（通过 `timesPressed`）两种情况。
4. **MCP nap 倒计时**：调用 `McpHttpServer.tickNapTasks()`——递减 MCP nap（延迟 HTTP 响应）任务的剩余 tick 计数器，使响应在请求的客户端刻延迟后触发。

顺序是有意的：界面跟踪最先运行，使后续操作看到正确的界面；WaitAlias 链其次执行；DropAlias 第三运行；MCP nap 最后触发，使延迟的 envelope 捕获反映当前 tick 的所有状态变化。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tick](tick.md) | `@Inject` 方法 |
| [WaitAlias.tasksWaiting](../../../alias/builtinAlias/WaitAlias.java/tasksWaiting.md) | 此处 tick 的延迟任务列表 |
| [DropAlias.tickDrop](../../../alias/builtinAlias/DropAlias.java/tickDrop.md) | 此处调用的持续丢弃驱动器 |
| [McpHttpServer.tickNapTasks](../../../mcp/McpHttpServer.java/tickNapTasks.md) | 此处调用的 nap 任务倒计时 |
| [McScreenHelper](../../../util/McScreenHelper.java/README.md) | 跨版本界面访问工具 |
