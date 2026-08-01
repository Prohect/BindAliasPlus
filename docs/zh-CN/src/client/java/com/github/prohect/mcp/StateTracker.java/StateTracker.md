# StateTracker（src/client/java/com/github/prohect/mcp/StateTracker.java）

## 语法

```java
public final class com.github.prohect.mcp.StateTracker
```

## 静态初始化

_无。_

## 备注

跟踪发送给 MCP 调用方的最近一次状态快照，并以两阶段模式（`begin` → `finish`）组装响应 envelope（状态信封）。驱动整个 MCP 状态投递管线：

**阶段 1 — `begin(full)`**：通过 `GameStateCollector.collect()` 对当前游戏状态做快照，与先前快照差分，并构建 JSON envelope 的开头：`{"client_tick":N, "state":{...}}`。状态成员规则：
- **完整模式**（`/state` 或世界变化时为 `full=true`）：包含每个成员。
- **差分模式**（其余所有工具为 `full=false`）：只包含发生变化的成员。消失的成员（例如容器关闭）序列化为 `null`。

`held_keys` 是例外——非空时它被**强制包含**进每个 envelope，因为界面切换会在后台重新应用按住的布尔别名，调用方必须始终知道当前按住了什么。

容器与快捷栏按槽位粒度差分：在 `/state`、打开界面、菜单身份变化时输出完整视图，此后只输出变化的槽位，外加变化时的 `empty_inv`/`container_grid` 或 `hotbar_empty`。

**阶段 2 — `finish(begun)`**：通过 `GameChannels.drain()` 排空所有消息 channel 并追加到 envelope：`"chat":[...], "mod":[...], "sound":[...], "recipe":[...]`。每条消息恰好投递一次；空 channel 省略。闭合 JSON 对象。

`reset()` 方法遗忘基线（在加入世界/断开连接时调用），使下一个 envelope 强制进入完整模式。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [begin](begin.md) | 阶段 1：快照与差分 |
| [finish](finish.md) | 阶段 2：排空 channel 并闭合 |
| [reset](reset.md) | 加入世界时遗忘基线 |
| [GameStateCollector.collect](GameStateCollector.java/collect.md) | 原始状态快照 |
| [GameChannels.drain](GameChannels.java/drain.md) | channel 消息排空 |
