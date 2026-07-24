# captureMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
private void captureMessage(net.minecraft.text.Text, org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `net.minecraft.text.Text` | The chat message received by the ChatHud. Its plain-text content is extracted via `getString()` and passed to `capture()`. |
| `ci` | `org.spongepowered.asm.mixin.injection.callback.CallbackInfo` | Mixin callback info — unused in this hook. |

## Remarks

This is the single `@Inject` hook that captures all chat messages in Yarn mappings. It targets `ChatHud.addMessage(Text)`, which is the unified entry point for client-side system messages, server-side system messages, and player chat messages.

In Mojang-mapped branches, this is split into three separate hooks:
- `onAddClientSystemMessage` → targets `ChatComponent.addClientSystemMessage(Component)`
- `onAddServerSystemMessage` → targets `ChatComponent.addServerSystemMessage(Component)`
- `onAddPlayerMessage` → targets `ChatComponent.addPlayerMessage(Component, MessageSignature, GuiMessageTag)`

The captured message is forwarded through `capture()` to `ChatCapture.onSystemMessage()` for collection during an active capture window.

## See Also

| Item | Description |
|------|-------------|
| [ChatComponentMixin](ChatComponentMixin.md) | Parent mixin class |
| [capture](capture.md) | Bridge method called by this hook |
| [ChatCapture.onSystemMessage](../../mcp/ChatCapture.java/onSystemMessage.md) | Ultimate destination of captured text |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAliasPlus/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
