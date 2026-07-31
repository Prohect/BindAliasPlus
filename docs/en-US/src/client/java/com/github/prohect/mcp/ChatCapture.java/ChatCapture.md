# ChatCapture (src/client/java/com/github/prohect/mcp/ChatCapture.java)

## Syntax

```java
public final class com.github.prohect.mcp.ChatCapture
```

## Static Initializer

_None._

## Remarks

Cross-thread capture of system chat messages for the MCP command-feedback pipeline. Used to collect `sendFeedback` output when an AI agent runs a command via the MCP HTTP endpoint.

**Mechanism**:
1. `begin()` clears the buffer and sets `active = true`. Called by the MCP HTTP handler before dispatching a command.
2. `ChatComponentMixin` fires on the render thread, calling `onSystemMessage(text)` for every system message. If `active`, the message is appended to the synchronized buffer.
3. `end()` sets `active = false`, drains the buffer atomically into a newline-joined string, and returns it. The buffer is cleared for the next capture window.

Thread-safety: `active` is a `volatile boolean` for visibility across threads. `buffer` is a `Collections.synchronizedList` for safe concurrent add/drain. The drain in `end()` snapshots under `synchronized(buffer)` to avoid a TOCTOU race.

## See Also

| Item | Description |
|------|-------------|
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/ChatComponentMixin.md) | Producer — hooks chat and calls `onSystemMessage()` |
| [McpHttpServer](../McpHttpServer.java/McpHttpServer.md) | Consumer — calls `begin()`/`end()` around command execution |
| [ScreenshotCapture](../ScreenshotCapture.java/ScreenshotCapture.md) | Companion capture class for screenshots |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
