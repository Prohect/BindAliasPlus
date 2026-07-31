# run method (src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java)

Adds a client-side-only chat message to the local HUD and chat channel.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.LocalSayAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The text to display locally |

## Remarks

1. If `Minecraft.player` is null (not in a world), returns immediately — no message can be displayed without an active game session.
2. Calls `Minecraft.gui.hud.getChat().addClientSystemMessage(Component.literal(args))` to display the text as a client-side system message.

The message appears in the player's chat overlay and chat history but is never sent to the server. It is visible only to the local player.

## See Also

| Item | Description |
|------|-------------|
| [SayAlias.run()](../SayAlias.java/run.md) | Sends chat to server (visible to all players) |
| [LogAlias.run()](../LogAlias.java/run.md) | Writes text to mod log |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
