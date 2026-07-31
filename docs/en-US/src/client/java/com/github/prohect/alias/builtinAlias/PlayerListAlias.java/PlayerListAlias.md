# PlayerListAlias (src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java)

Switch alias (`+playerList` / `-playerList`) that simulates holding/releasing the player-list key (Tab). Shows the online-player overlay while held. Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.PlayerListAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.PlayerListAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinPlayerList` (internal, exposed via `+playerList` / `-playerList`).

**Behavior:**
- `+playerList` (flag=1): Presses the vanilla `keyPlayerList` keybinding (Tab), making the online-player overlay visible and incrementing `clickCount` for the first-press tick behavior.
- `-playerList` (flag=0): Releases the `keyPlayerList` keybinding.

**Use case:** Primarily useful for an agent/MCP to capture a screenshot of the player list overlay to identify who is online on the server.

**Screen suppression:** The press event (`+playerList`) is cancelled when `Alias.isUnderTextInputScreen()` returns true. The release event is never suppressed.

**Reapply behavior:** Inherits `reapplyToGameKeyMapping()` from `BuiltinAliasWithBooleanArgs` — after a screen transition, if `flag` is true, the key is re-applied to the game's key mapping. Listed in `ReapplyAlias.SUPPORTED_ACTIONS` as `"playerList"`.

## See Also

| Item | Description |
|------|-------------|
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Reapply held keys after screen transitions |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |
| [SilentAlias](../SilentAlias.java/SilentAlias.md) | Switch alias for silent mode (another non-movement switch) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
