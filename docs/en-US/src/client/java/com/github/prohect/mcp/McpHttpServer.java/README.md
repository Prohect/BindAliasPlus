# McpHttpServer

HTTP JSON-RPC server running on `localhost` with a configurable port. Provides the external API surface for MCP (Model Context Protocol) tool calls: game-state snapshots, alias execution (with optional deferred nap responses), alias definition, CFG read/write, screenshot capture, and recipe listing.

**Recommended reading order:** start with the class doc, then the lifecycle methods ([start](start.md), [stop](stop.md), [tickNapTasks](tickNapTasks.md)), then the endpoint handlers ([handleState](handleState.md), [handleRunAlias](handleRunAlias.md), …).

## Fields

| Name | Type | Description |
|------|------|-------------|
| `DEFAULT_PORT` | `int` (static, private, `8095`) | Default HTTP listen port |
| `MAX_PORT_ATTEMPTS` | `int` (static, private, `10`) | Maximum ports to try if the default is occupied |
| `TIMEOUT_SECONDS` | `int` (static, private, `120`) | HTTP read timeout in seconds |
| `MAX_NAP_TICKS` | `long` (static, private, `600`) | Maximum nap delay allowed (~30 seconds at 20 TPS) |
| `NAP_TIMEOUT_MARGIN_MS` | `long` (static, private, `300_000`) | Wall-clock safety timeout for nap tasks (5 minutes) |
| `server` | `HttpServer` (static, private) | The JDK HTTP server instance |
| `port` | `int` (static, private) | The actual bound port |

## Methods

**Lifecycle:**

| Name | Signature | Description |
|------|-----------|-------------|
| [start](start.md) | `static void start()` | Starts the HTTP server on a daemon thread with port fallback |
| [stop](stop.md) | `static void stop()` | Stops the server with a 2-second grace period |
| [port](port.md) | `static int port()` | Returns the actual bound port |
| [tickNapTasks](tickNapTasks.md) | `static void tickNapTasks()` | Decrements nap task counters each client tick; completes futures when they reach zero |

**Threading:**

| Name | Signature | Description |
|------|-----------|-------------|
| [onMainThread](onMainThread.md) | `static <T> T onMainThread(CheckedSupplier<T> supplier)` | Ensures the operation runs on the Minecraft main thread |

**Endpoint handlers:**

| Name | Signature | Description |
|------|-----------|-------------|
| [handleState](handleState.md) | `static void handleState(HttpExchange ex)` | `GET /state` — returns full game-state snapshot |
| [handleScreenshot](handleScreenshot.md) | `static synchronized void handleScreenshot(HttpExchange ex)` | `GET /screenshot` — takes and returns PNG screenshot as base64 |
| [handleRunAlias](handleRunAlias.md) | `static void handleRunAlias(HttpExchange ex)` | `POST /runAlias?def=…&nap=…` — executes alias chain with optional deferred response |
| [handleDefineAlias](handleDefineAlias.md) | `static void handleDefineAlias(HttpExchange ex)` | `POST /defineAlias?name=…&def=…` — defines a new alias at runtime |
| [handleReadCFG](handleReadCFG.md) | `static void handleReadCFG(HttpExchange ex)` | `GET /readCFG` — returns current CFG content |
| [handleWriteCFG](handleWriteCFG.md) | `static void handleWriteCFG(HttpExchange ex)` | `POST /writeCFG?content=…` — overwrites CFG and reloads |
| [handleListRecipes](handleListRecipes.md) | `static void handleListRecipes(HttpExchange ex)` | `POST /listRecipes[?query=…]` — lists unlocked recipes |

**Utilities:**

| Name | Signature | Description |
|------|-----------|-------------|
| [parseQuery](parseQuery.md) | `static Map<String, String> parseQuery(String query)` | Parses URL query string into key-value pairs |
| [decodePercent](decodePercent.md) | `static String decodePercent(String s)` | Percent-decodes a string |
| [sendJson](sendJson.md) | `static void sendJson(HttpExchange ex, int code, String json)` | Sends a JSON HTTP response |
| [extractJsonStringField](extractJsonStringField.md) | `static String extractJsonStringField(String json, String fieldName)` | Minimal JSON field value extractor |

## See Also

| Item | Description |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | Produces the state envelope for all endpoints |
| [ScreenshotCapture](ScreenshotCapture.java/README.md) | Shared state for the screenshot pipeline |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | Read side for `listRecipes` |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | Calls `tickNapTasks()` each tick |
