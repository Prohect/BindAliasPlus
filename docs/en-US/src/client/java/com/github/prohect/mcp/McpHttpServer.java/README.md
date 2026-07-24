# McpHttpServer

## Fields

| Name | Type | Description |
|------|------|-------------|
| `PORT` | `static final int` | Server port (`25575`). |
| `TIMEOUT_SECONDS` | `static final int` | Timeout for `onMainThread()` calls (`5`). |
| `CONTAINER_JSON_MAX` | `static final int` | Max length for `/state` container section before it's rejected (`6000`). |
| `server` | `static HttpServer` | The running server instance, or `null` if stopped. |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| `start` | `public static void ()` | Starts the HTTP server. Idempotent. |
| `stop` | `public static void ()` | Stops the HTTP server with no grace period. |
| `onMainThread` | `private static <T> T (CheckedSupplier<T>)` | Runs a task on the game thread and blocks for result (5s timeout). |
| `parseQuery` | `private static Map<String,String> (String)` | Parses URL query string into key-value map. |
| `decode` | `private static String (String)` | Decodes `%XX` sequences. No `+` → space conversion. |
| `sendJson` | `private static void (HttpExchange, int, String)` | Sends a JSON HTTP response. |
| `j` | `private static String (String)` | Escapes a string for JSON (handles `"`, `\\`, `\n`, `\r`, `\t`). |
| `handleState` | `static void (HttpExchange)` | `GET /state` — full game state snapshot. |
| `buildContainerJson` | `private static String (AbstractContainerMenu)` | Builds compressed container slot JSON for `/state`. |
| `handleScreenshot` | `static synchronized void (HttpExchange)` | `GET /screenshot` — captures and returns base64 PNG. |
| `handleRunAlias` | `static void (HttpExchange)` | `POST /runAlias` — executes alias chain. |
| `handleDefineAlias` | `static void (HttpExchange)` | `POST /defineAlias` — defines alias via command pipeline. |
| `handleReadCFG` | `static void (HttpExchange)` | `GET /readCFG` — returns config file content. |
| `handleWriteCFG` | `static void (HttpExchange)` | `POST /writeCFG` — overwrites config and reloads. |

## See Also

| Item | Description |
|------|-------------|
| [handleState](handleState.md) | Most comprehensive endpoint — see for state schema |
| [start](start.md) | Server initialization |
| [ScreenshotCapture](../ScreenshotCapture.java/README.md) | Companion for screenshot capture pipeline |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAliasPlus/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
