# McpHttpServer (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public final class com.github.prohect.mcp.McpHttpServer
```

## Static Initializer

_None._

## Remarks

Lightweight HTTP server embedded in the Minecraft client for AI agent control via MCP (Model Context Protocol). Listens on `127.0.0.1:25575` and exposes REST endpoints for game state inspection, alias execution, config management, and screenshot capture.

**Threading model**: The server uses a daemon `CachedThreadPool` for HTTP handlers. Operations that touch Minecraft state (player, world, screens) are dispatched to the main game thread via `onMainThread()` with a 5-second timeout.

**Endpoints**:
| Method | Path | Description |
|--------|------|-------------|
| GET | `/state` | Full game state snapshot (player pos, health, held item, screen, container) |
| GET | `/screenshot` | Triggers screenshot and returns base64-encoded PNG with path/name metadata |
| POST | `/runAlias?def=...` | Executes alias chain and returns result |
| POST | `/defineAlias?name=...&def=...` | Defines alias via command pipeline, captures feedback |
| GET | `/readCFG` | Returns raw config file content |
| POST | `/writeCFG` | Overwrites config and reloads |

## See Also

| Item | Description |
|------|-------------|
| [start](start.md) | Lifecycle — creates server and registers endpoints |
| [onMainThread](onMainThread.md) | Thread bridge to the game loop |
| [ScreenshotCapture](../ScreenshotCapture.java/ScreenshotCapture.md) | Screenshot state shared with NativeImageMixin |
| [ChatCapture](../ChatCapture.java/ChatCapture.md) | Command feedback capture used by defineAlias |
| [BindAliasClient](../../BindAliasClient.java/BindAliasClient.md) | Client entry point that calls `start()` |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAlias/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
