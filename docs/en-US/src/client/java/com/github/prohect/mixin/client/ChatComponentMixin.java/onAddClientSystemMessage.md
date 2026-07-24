# onAddClientSystemMessage method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Inject(method = "addClientSystemMessage(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
private void onAddClientSystemMessage(Component message, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `message` | `Component` | The client-side system message being added to the chat HUD. Contains overlay text and command feedback from the integrated server. |
| `ci` | `CallbackInfo` | Mixin callback (unused — injection is non-cancellable at HEAD). |

## Remarks

Injected at the head of `ChatComponent.addClientSystemMessage`. Extracts the plain-text string from the `Component` and forwards it to `capture()`.

This hook covers text that appears only on the local client, such as `/alias` command output and mod-generated overlay messages. It does **not** capture server-broadcast system messages (those go through `onAddServerSystemMessage`).

## See Also

| Item | Description |
|------|-------------|
| [capture](capture.md) | Forwards to `ChatCapture.onSystemMessage()` |
| [onAddServerSystemMessage](onAddServerSystemMessage.md) | Parallel hook for server system messages |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
