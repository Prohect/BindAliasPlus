# onAddPlayerMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addPlayerMessage(Lnet/minecraft/network/chat/Component;"
        + "Lnet/minecraft/network/chat/MessageSignature;"
        + "Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
        at = @At("HEAD"))
private void onAddPlayerMessage(Component message, MessageSignature signature, GuiMessageTag tag, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `Component` | The player chat message text. |
| `signature` | `MessageSignature` | Cryptographic signature of the message (for chat reporting). Unused by the capture. |
| `tag` | `GuiMessageTag` | Visual tag applied to the message in the chat HUD (e.g. "Modified", "Not Secure"). Unused by the capture. |
| `ci` | `CallbackInfo` | Mixin callback (unused). |

## Remarks

Injected at the head of `ChatComponent.addPlayerMessage`. Captures all player-sent chat messages for potential future use (currently captured but not consumed separately from system messages).

The method signature is split across multiple lines in source to accommodate the verbose Mixin descriptor format for the 3-parameter overload.

## See Also

| Item | Description |
|------|-------------|
| [capture](capture.md) | Forwards to `ChatCapture.onSystemMessage()` |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
