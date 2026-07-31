# ChatComponentMixin (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Mixin(ChatComponent.class)
public class com.github.prohect.mixin.client.ChatComponentMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.gui.components.ChatComponent` to intercept all three public message-entry points (client system, server system, player messages) and feed the extracted plain-text message into the [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md) channel. This is the sole source for the `chat` array in the MCP response envelope. Each injection runs at `HEAD` of the target method, capturing the message via `Component.getString()` before vanilla renders it.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | Destination channel hub for captured messages |
| [onAddClientSystemMessage](onAddClientSystemMessage.md) | Injects into `addClientSystemMessage` |
| [onAddServerSystemMessage](onAddServerSystemMessage.md) | Injects into `addServerSystemMessage` |
| [onAddPlayerMessage](onAddPlayerMessage.md) | Injects into `addPlayerMessage` |
| [capture](capture.md) | Private helper that posts to the channel |
