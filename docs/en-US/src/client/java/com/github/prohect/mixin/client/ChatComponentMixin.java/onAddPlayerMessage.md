# onAddPlayerMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addPlayerMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V", at = @At("HEAD"))
private void onAddPlayerMessage(Component message, MessageSignature signature, GuiMessageTag tag, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `net.minecraft.network.chat.Component` | The player chat message being added to the HUD |
| `signature` | `net.minecraft.network.chat.MessageSignature` | The message's cryptographic signature (unused) |
| `tag` | `net.minecraft.client.multiplayer.chat.GuiMessageTag` | The GUI message tag (unused) |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `ChatComponent#addPlayerMessage(Component, MessageSignature, GuiMessageTag)`. Extracts the plain-text string from the component via `message.getString()` and posts it to [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md). This is the injection point that captures actual player chat messages sent on the server.

## See Also

| Item | Description |
|------|-------------|
| [capture](capture.md) | The private helper that posts to the channel |
| [GameChannels.CHAT](../../../mcp/GameChannels.java/CHAT.md) | Destination channel field |
