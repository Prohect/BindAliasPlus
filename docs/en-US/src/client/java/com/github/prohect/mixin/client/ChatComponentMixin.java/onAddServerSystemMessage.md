# onAddServerSystemMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
private void onAddServerSystemMessage(Component message, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `Component` | The server-side system message being added to chat. Typically server announcements, command responses, or death messages. |
| `ci` | `CallbackInfo` | Mixin callback (unused). |

## Remarks

Injected at the head of `ChatComponent.addServerSystemMessage`. Extracts plain-text and forwards to `capture()`.

This captures messages broadcast by a remote server (or the integrated server in single-player). Combined with `onAddClientSystemMessage`, the capture covers all system-level chat output.

## See Also

| Item | Description |
|------|-------------|
| [capture](capture.md) | Forwards to `ChatCapture.onSystemMessage()` |
| [onAddClientSystemMessage](onAddClientSystemMessage.md) | Parallel hook for client-side system messages |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
