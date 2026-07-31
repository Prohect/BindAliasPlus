# SendCommandAlias (src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java)

Builtin alias that sends a server command (without leading `/`). Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SendCommandAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.SendCommandAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `sendCommand` — usage: `sendCommand\command` (no leading `/` needed).

**Behavior:** Sends the argument string as a command to the server via `player.connection.sendCommand(args)`. This is the equivalent of typing `/command` into the chat box.

**Quoting:** Multi-argument commands must be quoted: `sendCommand\"tp @p ~ ~10 ~"`. The quotes are handled by the alias chain parser, not this alias.

**Requirements:** `mc.player` must be non-null. Returns silently if null.

**No screen suppression:** This alias works on any screen. It sends the command directly to the server connection, bypassing the chat GUI entirely.

**Difference from `say`:** `sendCommand` sends a server command (interpreted by the server). `say` sends plain chat text visible to other players.

**Important:** Do NOT include a leading `/` in the command string. The `sendCommand()` method already handles command routing.

**Examples:**
- `sendCommand\tp @p 0 70 0` — teleports the player
- `sendCommand\give @s diamond 64` — gives items
- `sendCommand\time set day` — sets time to day

## See Also

| Item | Description |
|------|-------------|
| [SayAlias](../SayAlias.java/SayAlias.md) | Send chat text instead of a command |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md) | Client-side-only message |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Base class for string-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
