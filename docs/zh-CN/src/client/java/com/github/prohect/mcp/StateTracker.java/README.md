# StateTracker

跟踪最近一次状态快照并组装 MCP 响应 envelope（状态信封）。采用两阶段模式（`begin` → `finish`）：在操作前对游戏状态做快照，操作后排空消息 channel。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `last` | `Map<String, String>`（静态，私有） | 用于差分的先前状态快照 |
| `lastContainer` | `GameStateCollector.ContainerSnapshot`（静态，私有） | 用于槽位级差分的先前容器快照 |
| `lastHotbarItems` | `Map<String, String>`（静态，私有） | 用于逐槽位差分的先前快捷栏物品 map |
| `lastHotbarEmpty` | `String`（静态，私有） | 先前的快捷栏空范围字符串 |
| `baselineJoinTick` | `long`（静态，私有） | 世界变化检测刻 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [reset](reset.md) | `static synchronized void reset()` | 遗忘基线（在加入世界/断开连接时调用） |
| [begin](begin.md) | `static synchronized String begin(boolean full)` | 对游戏状态做快照、与先前差分、构建 envelope 开头 |
| [finish](finish.md) | `static String finish(String begun)` | 把 channel 排空进 envelope 并闭合它 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameStateCollector](GameStateCollector.java/README.md) | 原始状态快照与 JSON 格式化 |
| [GameChannels](GameChannels.java/README.md) | 在 `finish` 中排空的消息 channel |
| [McpHttpServer](McpHttpServer.java/README.md) | 为每个请求调用 `begin`/`finish` 的 MCP 服务器 |
