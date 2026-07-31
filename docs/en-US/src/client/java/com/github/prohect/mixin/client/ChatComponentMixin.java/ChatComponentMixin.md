# ChatComponentMixin (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Mixin(ChatComponent.class)
public class com.github.prohect.mixin.client.ChatComponentMixin
```

## Static Initializer

_None._

## Remarks

Mixin that hooks three public message-entry points on `ChatComponent` to capture chat text for the MCP command-feedback system. Each injection calls `capture()`, which forwards the plain-text message to `ChatCapture.onSystemMessage()`.

The three hook points cover:
- **Client system messages** — overlay text, command feedback from the integrated server
- **Server system messages** — chat-visible system messages from a remote server
- **Player messages** — regular chat messages (captured for potential future use)

All injections use `@At("HEAD")` to capture every message before vanilla rendering. The `capture()` method is `private static` — no instance state needed, static dispatch avoids allocation overhead.

## See Also

| Item | Description |
|------|-------------|
| [ChatCapture](../../mcp/ChatCapture.java/ChatCapture.md) | Receives captured text and manages the capture window |
| [McpHttpServer](../../mcp/McpHttpServer.java/McpHttpServer.md) | Consumes captured text via the `/command-feedback` endpoint |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
