# MinecraftClientMixin（src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java）

## 语法

```java
@Mixin(value = Minecraft.class)
public class com.github.prohect.mixin.client.MinecraftClientMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.Minecraft` 的 `tick()`。这是中央的每客户端刻集成点，按明确定义的顺序驱动多个子系统：

1. **界面跟踪**：通过 [`McScreenHelper.getCurrentScreen()`](../../../util/McScreenHelper.java/getCurrentScreen.md) 更新 `BindAliasClient.currentScreen`，为所有别名的界面类型检查提供跨版本界面访问。
2. **WaitAlias 定时器**：遍历 `WaitAlias.tasksWaiting`，对每个延迟任务调用 `tick()`，并将已完成的任务从列表中压缩移除。
3. **持续丢弃**：驱动 `DropAlias.tickDrop()` —— 在 `+drop` 别名按住时处理每刻丢弃逻辑，覆盖容器界面（通过 `slotClicked`）和 3D 游戏视图（通过 `clickCount`）。
4. **MCP nap 倒计时**：调用 `McpHttpServer.tickNapTasks()` —— 递减 MCP nap（延迟 HTTP 响应）任务的剩余刻计数器，使响应在请求的客户端刻延迟后触发。

顺序是有意为之：界面跟踪最先运行，以便后续操作看到正确的界面；接着执行 WaitAlias 链；第三运行 DropAlias；MCP nap 最后触发，使延迟捕获的 envelope 反映当前刻的所有状态变更。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tick](tick.md) | `@Inject` 方法 |
| [WaitAlias.tasksWaiting](../../../alias/builtinAlias/WaitAlias.java/tasksWaiting.md) | 此处逐刻处理的延迟任务列表 |
| [DropAlias.tickDrop](../../../alias/builtinAlias/DropAlias.java/tickDrop.md) | 此处调用的持续丢弃驱动器 |
| [McpHttpServer.tickNapTasks](../../../mcp/McpHttpServer.java/tickNapTasks.md) | 此处调用的 nap 任务倒计时 |
| [McScreenHelper](../../../util/McScreenHelper.java/README.md) | 跨版本界面访问工具 |
