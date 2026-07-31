# ChatComponentMixin (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Mixin(ChatHud.class)
public class com.github.prohect.mixin.client.ChatComponentMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.gui.hud.ChatHud` to intercept the single `addMessage(Text)` method — the unified entry point for all HUD-bound chat messages in Minecraft 1.21.x. On this version, the three separate `add*Message` methods from earlier Minecraft versions (`addClientSystemMessage`, `addServerSystemMessage`, `addPlayerMessage`) have been consolidated into a single `addMessage(Text)` override, so a single `@Inject` at `HEAD` captures all message types: server-side system messages (join/leave, command feedback), client-side system messages (overlay text, action bar), and player chat messages. The plain-text message is extracted via `Text#getString()` and posted to [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md). This is the sole source for the `chat` array in the MCP response envelope.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | Destination channel hub for captured messages |
| [captureMessage](captureMessage.md) | The `@Inject` that captures all message types |
| [capture](capture.md) | Private helper that posts to the channel |
