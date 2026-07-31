# McpHttpServer (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public final class com.github.prohect.mcp.McpHttpServer
```

## Static Initializer

_None._

## Remarks

HTTP JSON-RPC server running on `localhost` with a configurable port. Starts on a daemon thread during client initialization and listens for MCP tool calls. Supports seven endpoints:

- `GET /state` — returns full game-state snapshot (via `StateTracker.begin(true)` + `finish`)
- `GET /screenshot` — takes and returns a PNG screenshot as base64
- `POST /runAlias` — executes an alias chain, returns state diff + optional nap (deferred response)
- `POST /defineAlias` — defines a new alias at runtime
- `GET /readCFG` — returns current CFG file content
- `POST /writeCFG` — overwrites CFG file and reloads
- `POST /listRecipes` — lists unlocked recipes (with optional query filtering)

Port selection tries the configured default (8095) and scans up to 9 additional ports if occupied. The server has a read timeout (120 seconds) to prevent hung connections. All game-state operations are marshalled to the Minecraft main thread via `onMainThread`. Deferred responses (nap) use `napTasks` — a list of `NapTask` records that are decremented each client tick by `tickNapTasks()` (called from `MinecraftClientMixin`).

## See Also

| Item | Description |
|------|-------------|
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | Calls `tickNapTasks()` each tick |
| [StateTracker](StateTracker.java/README.md) | Produces the state envelope for all endpoints |
| [ScreenshotCapture](ScreenshotCapture.java/README.md) | Shared state for the screenshot pipeline |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | Read side for `listRecipes` |
| [start](start.md) | Server startup |
| [tickNapTasks](tickNapTasks.md) | Nap task tick countdown |
