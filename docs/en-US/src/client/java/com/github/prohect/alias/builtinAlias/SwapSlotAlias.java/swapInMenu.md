# swapInMenu method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Swaps the contents of two slots inside an open container menu, choosing the best strategy based on slot addressability.

## Syntax

```java
private static void swapInMenu(net.minecraft.client.multiplayer.MultiPlayerGameMode, net.minecraft.world.inventory.AbstractContainerMenu, net.minecraft.world.inventory.Slot, net.minecraft.world.inventory.Slot, net.minecraft.client.player.LocalPlayer)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| interactionManager | MultiPlayerGameMode | The game's interaction manager for handling container clicks |
| menu | AbstractContainerMenu | The open container menu |
| slot0 | Slot | First slot to swap |
| slot1 | Slot | Second slot to swap |
| player | LocalPlayer | The local player (for click context) |

## Remarks

**Algorithm (three strategies, tried in order):**

1. **SWAP via slot0:** If `slot0` is hotbar/offhand-addressable (`swapButton(slot0) != -1`), perform a single `ContainerInput.SWAP` click on `slot1` with `button0`. This swaps the hotbar item with slot1's item.

2. **SWAP via slot1:** If `slot1` is hotbar/offhand-addressable, perform a single SWAP click on `slot0` with `button1`.

3. **PICKUP fallback sequence:** If neither slot is hotbar-addressable:
   - Click `slot0` with `ContainerInput.PICKUP` (pick up slot0's item).
   - Click `slot1` with `ContainerInput.PICKUP` (place slot0's item into slot1, pick up slot1's item).
   - If cursor still has an item (slot0 was not a take-only slot): click `slot0` with PICKUP to put slot1's item back.
   - If cursor STILL has an item (slot0 rejected the put-back — it's a take-only slot like crafting result): click `slot1` with PICKUP to restore slot1's original item.
   - If cursor STILL has an item: log a warning about an orphaned item stack on the cursor.

**SWAP path limitation:** Vanilla's SWAP click is all-or-nothing. If the hotbar/offhand item cannot be placed into the container slot (e.g., non-fuel into furnace fuel slot, any item into result slot), the server silently rejects the entire swap and neither item moves. For taking items from restricted slots, use an empty hotbar slot or swap with a non-hotbar inventory slot (10-36) to fall through to the PICKUP path, which handles rejection gracefully.

**Return value:** void.

**Side effects:** Mutates the item stacks in both slots. Sends click packets to the server.

**Error handling:** Handles the case where slot0 rejects the put-back (take-only slot) by restoring slot1's original item.

## See Also

| Item | Description |
|------|-------------|
| [swapButton](swapButton.md) | Determines SWAP-click addressability |
| [clickSlot](clickSlot.md) | Low-level slot click handler |
| [run](run.md) | Main run method that calls this |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
