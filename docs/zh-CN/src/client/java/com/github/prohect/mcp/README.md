# mcp

MCP（Model Context Protocol，模型上下文协议）模块——HTTP JSON-RPC 服务器及配套基础设施，为 AI agent 的工具调用暴露外部 API 表面。运行在 `localhost` 上，端口可配置。

**推荐阅读顺序：** 从 [`McpHttpServer`](McpHttpServer.java/README.md)（核心服务器）开始，然后是 [`StateTracker`](StateTracker.java/README.md) + [`GameStateCollector`](GameStateCollector.java/README.md)（状态投递管线），接着是 [`GameChannels`](GameChannels.java/README.md)（消息中心），最后是供数方（[`SoundCapture`](SoundCapture.java/README.md)、[`RecipeBookHelper`](RecipeBookHelper.java/README.md)）与截图管线（[`ScreenshotCapture`](ScreenshotCapture.java/README.md)）。

## 目录

| 名称 | 说明 |
|------|-------------|
| [GameChannels.java](GameChannels.java/README.md) | 基于 channel（消息通道）的消息中心，含四个命名 channel（chat、mod、sound、recipe）——线程安全发布、重复声音合并，以及模组日志的 Log4j appender |
| [GameStateCollector.java](GameStateCollector.java/README.md) | 把原始游戏状态快照（玩家位置、生命值、物品栏、界面信息等）组装成 JSON 片段，另含容器/快捷栏槽位级差分与共享格式化辅助方法 |
| [McpHttpServer.java](McpHttpServer.java/README.md) | localhost 上的 HTTP JSON-RPC 服务器——七个 endpoint（端点）分别处理状态、截图、别名执行、别名定义、CFG 读写与配方列表，支持可选的延迟 nap 响应 |
| [RecipeBookHelper.java](RecipeBookHelper.java/README.md) | 客户端配方书的读取侧——列出已解锁配方并附实时可合成状态，按物品 ID 或语言名称解析查询，并为 `listRecipes` endpoint 提供差分簿记 |
| [ScreenshotCapture.java](ScreenshotCapture.java/README.md) | 截图捕获管线的共享状态——持有一次性 future，用于从 `NativeImageMixin` 到 `McpHttpServer` 的内存 PNG 字节传输 |
| [SoundCapture.java](SoundCapture.java/README.md) | 客户端声音管理器上的 `SoundEventListener`——把能出字幕的声音及精确方位信息送入 `SOUND` channel |
| [StateTracker.java](StateTracker.java/README.md) | 跟踪最新状态快照，以 `begin`/`finish` 两阶段模式组装 MCP 响应 envelope——在成员与槽位粒度上对状态做差分，排空消息 channel |
