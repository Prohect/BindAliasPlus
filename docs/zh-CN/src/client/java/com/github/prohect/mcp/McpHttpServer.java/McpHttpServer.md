# McpHttpServer（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
public final class com.github.prohect.mcp.McpHttpServer
```

## 静态初始化

_无。_

## 备注

运行在 `localhost` 上、端口可配置的 HTTP JSON-RPC 服务器。在客户端初始化期间于守护线程上启动，监听 MCP 工具调用。支持七个 endpoint（端点）：

- `GET /state` — 返回完整游戏状态快照（经 `StateTracker.begin(true)` + `finish`）
- `GET /screenshot` — 拍摄并以 base64 返回 PNG 截图
- `POST /runAlias` — 执行别名链，返回状态差分 + 可选的 nap（延迟响应）
- `POST /defineAlias` — 在运行时定义新别名
- `GET /readCFG` — 返回当前 CFG 文件内容
- `POST /writeCFG` — 覆盖 CFG 文件并重新加载
- `POST /listRecipes` — 列出已解锁配方（支持可选的查询过滤）

端口选择：先尝试配置的默认端口（8095），若被占用则最多再扫描 9 个额外端口。服务器设有读超时（120 秒）以防连接挂起。所有游戏状态操作都通过 `onMainThread` 编组到 Minecraft 主线程执行。延迟响应（nap）使用 `napTasks`——一个 `NapTask` 记录列表，每个客户端刻由 `tickNapTasks()`（从 `MinecraftClientMixin` 调用）递减。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | 每个刻调用 `tickNapTasks()` |
| [StateTracker](StateTracker.java/README.md) | 为所有 endpoint 产出状态 envelope |
| [ScreenshotCapture](ScreenshotCapture.java/README.md) | 截图管线的共享状态 |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | `listRecipes` 的读取侧 |
| [start](start.md) | 服务器启动 |
| [tickNapTasks](tickNapTasks.md) | nap 任务的刻倒计时 |
