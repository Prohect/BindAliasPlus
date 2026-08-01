# McpHttpServer

运行在 `localhost` 上、端口可配置的 HTTP JSON-RPC 服务器。为 MCP（Model Context Protocol，模型上下文协议）工具调用提供外部 API 表面：游戏状态快照、别名执行（支持可选的延迟 nap 响应）、别名定义、CFG 读写、截图捕获与配方列表。

**推荐阅读顺序：** 从类文档开始，然后是生命周期方法（[start](start.md)、[stop](stop.md)、[tickNapTasks](tickNapTasks.md)），最后是 endpoint（端点）处理器（[handleState](handleState.md)、[handleRunAlias](handleRunAlias.md) 等）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `DEFAULT_PORT` | `int`（静态，私有，`8095`） | 默认 HTTP 监听端口 |
| `MAX_PORT_ATTEMPTS` | `int`（静态，私有，`10`） | 默认端口被占用时尝试的最大端口数 |
| `TIMEOUT_SECONDS` | `int`（静态，私有，`120`） | HTTP 读超时（秒） |
| `MAX_NAP_TICKS` | `long`（静态，私有，`600`） | 允许的最大 nap 延迟（20 TPS 下约 30 秒） |
| `NAP_TIMEOUT_MARGIN_MS` | `long`（静态，私有，`300_000`） | nap 任务的墙钟安全超时（5 分钟） |
| `server` | `HttpServer`（静态，私有） | JDK HTTP 服务器实例 |
| `port` | `int`（静态，私有） | 实际绑定的端口 |

## 方法

**生命周期：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [start](start.md) | `static void start()` | 在守护线程上启动 HTTP 服务器，带端口回退 |
| [stop](stop.md) | `static void stop()` | 以 2 秒宽限期停止服务器 |
| [port](port.md) | `static int port()` | 返回实际绑定的端口 |
| [tickNapTasks](tickNapTasks.md) | `static void tickNapTasks()` | 每个客户端刻递减 nap 任务计数；计数归零时完成 future |

**线程：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onMainThread](onMainThread.md) | `static <T> T onMainThread(CheckedSupplier<T> supplier)` | 确保操作在 Minecraft 主线程上运行 |

**Endpoint 处理器：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [handleState](handleState.md) | `static void handleState(HttpExchange ex)` | `GET /state` — 返回完整游戏状态快照 |
| [handleScreenshot](handleScreenshot.md) | `static synchronized void handleScreenshot(HttpExchange ex)` | `GET /screenshot` — 拍摄并以 base64 返回 PNG 截图 |
| [handleRunAlias](handleRunAlias.md) | `static void handleRunAlias(HttpExchange ex)` | `POST /runAlias?def=…&nap=…` — 执行别名链，支持可选延迟响应 |
| [handleDefineAlias](handleDefineAlias.md) | `static void handleDefineAlias(HttpExchange ex)` | `POST /defineAlias?name=…&def=…` — 在运行时定义新别名 |
| [handleReadCFG](handleReadCFG.md) | `static void handleReadCFG(HttpExchange ex)` | `GET /readCFG` — 返回当前 CFG 内容 |
| [handleWriteCFG](handleWriteCFG.md) | `static void handleWriteCFG(HttpExchange ex)` | `POST /writeCFG?content=…` — 覆盖 CFG 并重新加载 |
| [handleListRecipes](handleListRecipes.md) | `static void handleListRecipes(HttpExchange ex)` | `POST /listRecipes[?query=…]` — 列出已解锁配方 |

**工具方法：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [parseQuery](parseQuery.md) | `static Map<String, String> parseQuery(String query)` | 把 URL 查询字符串解析为键值对 |
| [decodePercent](decodePercent.md) | `static String decodePercent(String s)` | 对字符串做百分号解码 |
| [sendJson](sendJson.md) | `static void sendJson(HttpExchange ex, int code, String json)` | 发送 JSON HTTP 响应 |
| [extractJsonStringField](extractJsonStringField.md) | `static String extractJsonStringField(String json, String fieldName)` | 极简 JSON 字段值提取器 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | 为所有 endpoint 产出状态 envelope |
| [ScreenshotCapture](ScreenshotCapture.java/README.md) | 截图管线的共享状态 |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | `listRecipes` 的读取侧 |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | 每个刻调用 `tickNapTasks()` |
