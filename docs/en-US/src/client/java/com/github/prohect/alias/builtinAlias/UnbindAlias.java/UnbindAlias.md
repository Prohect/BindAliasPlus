# UnbindAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnbindAlias.java)

Builtin alias that sends an `unbind` command to the server to remove a keybinding. Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnbindAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.UnbindAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unbind` — usage: `unbind\key` or `unbind\"key with spaces"`.

**Behavior:** Constructs an `unbind` command line starting with `"unbind"` followed by a space (the `divider4AliasDefinition` separator), then appends the args string. The assembled command is sent to the server via `player.connection.sendCommand(line)`. Note: this calls `sendCommand` (sends as a server command, meaning it gets forwarded to the server for processing), not `sendChat`.

**Why `sendCommand` not `sendChat`:** The `unbind` command is processed by the mod's server-side command handler. The leading `/` is implicit in `sendCommand`.

**Argument processing:** The args string has its backslash separators (`\`) replaced with the alias definition separator (space). This means the command line uses spaces between arguments rather than backslashes.

**Requirements:** `mc.player` must be non-null. Logs a warning if null.

**No screen suppression:** Works on any screen — it's a command, not a game input.

**Relationship with `UnloadCFGBindsAlias` and `UnloadUserBindsAlias`:** These unload aliases remove keybindings programmatically from the client's `BINDING_PLUS` map. The `unbind` alias sends the command to the server-side handler instead.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Remove CFG-loaded bindings programmatically |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | Remove runtime bindings programmatically |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md) | The underlying `sendCommand` used to dispatch |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
