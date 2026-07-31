# swapInMenu method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Swaps the contents of two slots inside an open container menu, choosing the best strategy based on slot addressability.

## Syntax

```java
private static void swapInMenu(net.minecraft.client.network.ClientPlayerInteractionManager, net.minecraft.screen.ScreenHandler, net.minecraft.screen.slot.Slot, net.minecraft.screen.slot.Slot, net.minecraft.client.network.ClientPlayerEntity)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| interactionManager | ClientPlayerInteractionManager | The game's interaction manager for handling container clicks (Yarn: `ClientPlayerInteractionManager`; Mojang: `MultiPlayerGameMode`) |
| menu | ScreenHandler | The open container menu (Yarn: `ScreenHandler`; Mojang: `AbstractContainerMenu`) |
| slot0 | Slot | First slot to swap |
| slot1 | Slot | Second slot to swap |
| player | ClientPlayerEntity | The local player (Yarn: `ClientPlayerEntity`; Mojang: `ClientPlayerEntity`) |

## Remarks

**Algorithm (three strategies, tried in order):**

1. **SWAP via slot0:** If `slot0` is hotbar/offhand-addressable (`swapButton(slot0) != -1`), perform a single `SlotActionType.SWAP` click on `slot1` with `button0`. This swaps the hotbar item with slot1's item.

2. **SWAP via slot1:** If `slot1` is hotbar/offhand-addressable, perform a single SWAP click on `slot0` with `button1`.

3. **PICKUP fallback sequence:** If neither slot is hotbar-addressable:
   - Click `slot0` with `SlotActionType.PICKUP` (pick up slot0's item).
   - Click `slot1` with `SlotActionType.PICKUP` (place slot0's item into slot1, pick up slot1's item).
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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
