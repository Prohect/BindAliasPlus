# captureMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
private void captureMessage(Text message, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `Text` | The chat message being added to the HUD |
| `ci` | `CallbackInfo` | Mixin callback (unused — injection is not cancellable) |

## Remarks

Injected at `HEAD` of `ChatHud#addMessage(Text)`, the single entry point through which all chat messages reach the HUD in Minecraft 1.21.x. On this version, the three separate `add*Message` methods from earlier Minecraft versions (`addClientSystemMessage`, `addServerSystemMessage`, `addPlayerMessage`) have been unified into a single `addMessage(Text)` method, so a single injection point captures all message types:

- **System messages** (server-side): join/leave notifications, command feedback, advancement announcements.
- **Client-side system messages**: overlay text, action bar messages.
- **Player chat messages**: messages sent by other players or the local player.

The injection runs at `HEAD` — before vanilla renders the message to the HUD — and calls the private static helper `capture(message.getString())` which posts the plain-text message to `GameChannels.CHAT`. No filtering or transformation is applied; the raw `Text#getString()` output is forwarded as-is.

This method is not cancellable. The vanilla message always reaches the HUD regardless of MCP capture status. To suppress chat messages from the HUD, use the `+silent` / `-silent` alias instead.

The 26.x (Mojang) branch used three separate `@Inject` methods targeting `addClientSystemMessage(Component)`, `addServerSystemMessage(Component)`, and `addPlayerMessage(Component, MessageSignature, GuiMessageTag)` respectively in `ChatComponent`. The 1.21.x branch consolidates these into the single `captureMessage` injection on the unified `addMessage(Text)` method of `ChatHud`.

## See Also

| Item | Description |
|------|-------------|
| [capture](capture.md) | The private helper that posts to the CHAT channel |
| [ChatComponentMixin](ChatComponentMixin.md) | The enclosing mixin class |
| [GameChannels.CHAT](../../../mcp/GameChannels.java/CHAT.md) | Destination channel |
| [StateTracker](../../../mcp/StateTracker.java/README.md) | Drains the CHAT channel into the MCP response envelope |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
