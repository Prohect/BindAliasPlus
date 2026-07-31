# AdvancementsAlias (src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java)

Builtin alias that opens or closes the advancements screen (default: L key). Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.AdvancementsAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.AdvancementsAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinAdvancements"`. Usage: `+advancements` to open, `-advancements` (no toggle effect — `-advancements` does nothing). Unlike other switch aliases, this key is polled via `consumeClick()` in vanilla's `Gui.java` — the screen opens when the key is **released**, not when pressed.

The implementation manipulates `Minecraft.options.keyAdvancements`:

- `setDown(flag)` — holds or releases the key
- `clickCount++` — on press, increments the click counter so that `consumeClick()` fires

Press events are suppressed when a text-input screen is open.

## See Also

| Item | Description |
|------|-------------|
| [PlayerListAlias](../PlayerListAlias.java/PlayerListAlias.md) | Similar toggle pattern for the player list overlay |
| [DebugOverlayAlias](../DebugOverlayAlias.java/DebugOverlayAlias.md) | Toggle debug overlay |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
