# SayAlias (src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java)

Builtin alias that sends a chat message to the server. Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SayAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.SayAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `say` — usage: `say\text` or `say\"multi word text"`.

**Behavior:** Sends the argument string as a chat message to the server via `player.connection.sendChat(args)`. This is the equivalent of typing the message into the chat box and pressing Enter.

**Quoting:** Multi-word messages must be double-quoted: `say\"Hello world"`. The quotes are part of the alias chain syntax (`getDefinitionSplits`/`getDefinitions`), not part of this alias's logic — the alias receives the text without quotes.

**Requirements:** `mc.player` must be non-null. Returns silently if null.

**No screen suppression:** This alias works on any screen (it bypasses the chat screen entirely). It sends the message directly to the server connection, not through the chat GUI.

**Difference from `localSay`:** `say` sends the message to the server (visible to all players). `localSay` creates a client-side-only chat message (visible only to the local player).

**Difference from `sendCommand`:** `say` sends plain chat text. `sendCommand` sends a server command (e.g., `/tp`, `/give`) — the leading `/` is implicit in `sendCommand`.

## See Also

| Item | Description |
|------|-------------|
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md) | Client-side-only chat message |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md) | Send a server command |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Base class for string-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
