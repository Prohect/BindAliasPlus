# SwapSlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Builtin alias that swaps two item stacks between any inventory or container slots. Extends `BuiltinAliasWithArgs`. This is one of the most complex builtin aliases, supporting both player inventory slots (1-41) and container menu slots (cN).

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SwapSlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SwapSlotAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `swapSlot` — usage: `swapSlot\a\b` (two slots) or `swapSlot\a` (swap with selected hotbar slot).

**Slot numbering (player inventory):**
- 1-9: Hotbar slots
- 10-36: Main inventory slots (top-left to bottom-right)
- 37: Feet armor slot
- 38: Legs armor slot
- 39: Chest armor slot
- 40: Head armor slot
- 41: Offhand slot

**Container slot references (cN):**
- `c1`, `c2`, ..., `cN` refer to the N-th slot (1-based) of the currently open container menu
- Works in any container screen: chest, crafting table, furnace, anvil, enchanting table, smithing table, grindstone, loom, stonecutter, merchant, etc.
- Container slots typically appear first in the menu slot list, with player inventory slots last

**One-argument form:** `swapSlot\a` swaps slot `a` with the currently selected hotbar slot.

**Two-argument form:** `swapSlot\a\b` swaps slot `a` with slot `b`.

**Variable support:** Slot arguments can be variable names resolved via `VarAlias.resolveInt()` or container-slot variables from `VarAlias.CONTAINER_SLOT_VARIABLES`.

**Three swap strategies (in order of preference):**

1. **Hotbar/offhand packet swap:** When both slots are hotbar (1-9) or offhand (41), uses a fast 3-way offhand swap sequence to exchange items without opening any screen. This works even while another container screen is open.

2. **SWAP click in menu:** When one of the slots is hotbar/offhand-addressable and a container screen is open, a single `ContainerInput.SWAP` click handles the exchange efficiently. **Limitation:** Vanilla SWAP is all-or-nothing — if the hotbar/offhand item can't be placed into the target container slot (e.g., non-fuel into furnace fuel slot), the server silently rejects the entire swap and neither item moves.

3. **PICKUP sequence in menu:** When neither slot is hotbar-addressable, uses a PICKUP-click sequence: pick up from slot0, click slot1, put back into slot0. If slot0 rejects the put-back (take-only slots like crafting results), it restores slot1. This path gracefully handles restricted slots.

**Screen handling:**
- When swapping between hotbar/offhand slots only: no screen needed (packet-based swap).
- When a container screen (non-inventory) is open: uses that screen's menu directly.
- Otherwise: opens the player inventory screen, performs the swap, then closes it.
- If any screen other than inventory/container is open and the swap needs a screen: returns silently (avoids closing an unexpected screen).

**Error handling:** Logs warnings for invalid slot numbers, identical slots, null player/inventory/network handler, and slots not found in the current menu.

## See Also

| Item | Description |
|------|-------------|
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system for slot argument resolution |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | Select a hotbar slot |
| [SwapHandAlias](../SwapHandAlias.java/SwapHandAlias.md) | Swap main hand with offhand |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Direct base class |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
