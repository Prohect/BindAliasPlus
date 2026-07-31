# DropAlias (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

Builtin alias that simulates the drop-item key binding (Q key). Supports continuous drop with an initial delay matching OS key-repeat behavior, and special handling for container screens. Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.DropAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DropAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinDrop"`. Usage: `+drop` to press, `-drop` to release. DropAlias is more complex than other switch aliases because it handles both an immediate first drop and continuous dropping after a delay.

**Initial drop:** On press (`+drop`), one immediate drop is performed — `onMouseClick(…, THROW)` in container screens, or `dropKey.timesPressed++` in the 3D game.

**Continuous drop (`tickDrop()`):** Driven every client tick from `MinecraftClientMixin` while `flag` is true. After an initial delay of `INITIAL_DELAY_TICKS` (3 ticks, matching the OS key-repeat gap), each tick fires another drop action.

**Container screen special behavior:** When a container screen (chest, furnace, inventory) is open, drops target the hovered slot using `onMouseClick()` with `SlotActionType.THROW`. Ctrl+click detection (`hasControlDown()`) controls whether the entire stack (button 1) or a single item (button 0) is dropped.

**Reapply:** After screen transitions, `reapplyToGameKeyMapping()` sets `dropKey.setPressed(true)` but does **not** increment `timesPressed` — this prevents an extra drop from firing when the cursor is re-locked.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `INITIAL_DELAY_TICKS` | `static final int` | Ticks to wait after press before continuous drops begin (3 ticks, matching OS key-repeat) |
| `ticksHeld` | `private long` | Ticks elapsed since the last press; reset to 0 on release |

## See Also

| Item | Description |
|------|-------------|
| [tickDrop](tickDrop.md) | Per-tick continuous drop driver |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | Reapply without extra drop |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | Calls `tickDrop()` each client tick |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
