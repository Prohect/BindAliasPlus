# PickItemAlias (src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java)

One-shot alias that triggers the vanilla pick-block/entity behavior — picks the block or entity the player is looking at onto the selected hotbar slot. Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.PickItemAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.PickItemAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `pickItem` (internal, exposed as `pickItem`).

**Behavior:** Simulates pressing the vanilla pick-block keybinding (`keyPickItem`, default: middle mouse button). Sets the keybinding to "down" and increments `clickCount`, causing the game to call `pickBlockOrEntity()` on the next polling cycle. This selects the hotbar slot matching the targeted block/entity, or moves a matching item from the inventory to the selected slot.

**Screen suppression:** The alias is cancelled when `Alias.isUnderTextInputScreen()` returns true.

**Requirements:** `mc.player` must be non-null (silently returns otherwise).

**Key differences from vanilla pick-block:**
- In Creative mode, the block/entity is directly given to the selected slot.
- In Survival mode, an item matching the targeted block/entity is moved (via SWAP) from the inventory to the selected slot if one exists.
- This alias triggers the exact same game logic path as the default middle-click keybinding.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Base class for one-shot aliases |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | Select hotbar slot directly (without pick-block logic) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
