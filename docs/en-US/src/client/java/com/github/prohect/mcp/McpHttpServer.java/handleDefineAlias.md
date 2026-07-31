# handleDefineAlias method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleDefineAlias(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange for `POST /defineAlias`. |

## Remarks

`POST /defineAlias?name=...&def=...` — defines a new alias by sending the `/alias <name> <def>` command through the vanilla command pipeline and capturing the feedback.

**Pipeline**:
1. Parses `name` and `def` from query parameters. Returns 400 if either is missing.
2. Calls `ChatCapture.begin()` to start a command-feedback capture window.
3. Dispatches `mc.player.connection.sendCommand("alias " + name + " " + def)` on the main thread via `onMainThread()`.
4. The command handler processes the alias definition and calls `sendFeedback()`, which the `ChatComponentMixin` captures into `ChatCapture`.
5. Calls `ChatCapture.end()` to drain and return the captured feedback.
6. If feedback starts with `"Alias "` (success message), returns `{"ok": true, "feedback": "..."}`. Otherwise returns `{"error": "..."}` with the feedback as the error message.

On exception, ensures `ChatCapture.end()` is called to reset the capture state, then returns error JSON with status 500.

## See Also

| Item | Description |
|------|-------------|
| [ChatCapture.begin](../ChatCapture.java/begin.md) | Starts the capture window |
| [ChatCapture.end](../ChatCapture.java/end.md) | Drains captured feedback |
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/ChatComponentMixin.md) | Captures the `sendFeedback` text |
| [handleRunAlias](handleRunAlias.md) | Executes aliases (separate from defining them) |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
