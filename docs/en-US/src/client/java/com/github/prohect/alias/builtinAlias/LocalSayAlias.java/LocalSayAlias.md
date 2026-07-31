# LocalSayAlias (src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java)

Builtin alias that displays a client-side-only chat message visible on the local player's HUD and chat channel. The message is not sent to the server. Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LocalSayAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.LocalSayAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"localSay"`. Usage: `localSay\<text>`.

The message is added via `Minecraft.gui.hud.getChat().addClientSystemMessage(Component.literal(args))`, which displays it in the client-side system message channel. It is **not** sent over the network — the server and other players never see it.

If the player is null (not in a world), the alias silently returns without doing anything.

**Use cases:** Debug logging during alias development, inline annotations visible only locally, or displaying computed values from variables.

## See Also

| Item | Description |
|------|-------------|
| [SayAlias](../SayAlias.java/SayAlias.md) | Sends a chat message to the server (visible to all) |
| [LogAlias](../LogAlias.java/LogAlias.md) | Writes text to the mod log file |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md) | Sends a server command |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
