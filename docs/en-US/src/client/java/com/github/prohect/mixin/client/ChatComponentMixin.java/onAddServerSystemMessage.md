# onAddServerSystemMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
private void onAddServerSystemMessage(Component message, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `net.minecraft.network.chat.Component` | The server-side system message being added to the HUD |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `ChatComponent#addServerSystemMessage(Component)`. Extracts the plain-text string from the component via `message.getString()` and posts it to [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md). Server system messages include join/leave announcements, death messages, command block output, and `/say` broadcasts.

## See Also

| Item | Description |
|------|-------------|
| [capture](capture.md) | The private helper that posts to the channel |
| [GameChannels.CHAT](../../../mcp/GameChannels.java/CHAT.md) | Destination channel field |
