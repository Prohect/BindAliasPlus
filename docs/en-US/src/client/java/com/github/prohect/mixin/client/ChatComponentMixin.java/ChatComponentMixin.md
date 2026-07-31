# ChatComponentMixin (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
@Mixin(net.minecraft.client.gui.hud.ChatHud.class)
public class com.github.prohect.mixin.client.ChatComponentMixin
```

## Static Initializer

_None._

## Remarks

This mixin injects into the chat HUD to capture all incoming messages during an active command-feedback collection window. In Yarn mappings, the vanilla `ChatComponent` class is named `ChatHud`, and the three separate message-entry methods (`addClientSystemMessage`, `addServerSystemMessage`, `addPlayerMessage`) are unified into a single `addMessage(Text)` method that receives all chat messages regardless of origin.

Correspondingly, this mixin provides a single `@Inject` hook (`captureMessage`) instead of the three separate hooks (`onAddClientSystemMessage`, `onAddServerSystemMessage`, `onAddPlayerMessage`) found on Mojang-mapped branches.

**Yarn mapping equivalents:**

| Mojang (official) | Yarn (1.21.x) |
|---|---|
| `ChatComponent` | `ChatHud` |
| `Component` | `Text` |
| `addClientSystemMessage(Component)` / `addServerSystemMessage(Component)` / `addPlayerMessage(Component, ...)` | `addMessage(Text)` (unified) |
| `onAddClientSystemMessage` / `onAddServerSystemMessage` / `onAddPlayerMessage` | `captureMessage` (unified) |

## See Also

| Item | Description |
|------|-------------|
| [ChatCapture](../../mcp/ChatCapture.java/ChatCapture.md) | Target class that collects captured message text |
| [ChatCapture.onSystemMessage](../../mcp/ChatCapture.java/onSystemMessage.md) | Static method called by `capture()` |
| [captureMessage](captureMessage.md) | The `@Inject` hook on `ChatHud.addMessage(Text)` |
| [capture](capture.md) | Private bridge to `ChatCapture.onSystemMessage()` |

*Documented for Commit: [6c62e00c173ab8ceb4be73871bf00ca3c1b63b32](https://github.com/Prohect/BindAlias/tree/6c62e00c173ab8ceb4be73871bf00ca3c1b63b32)*
