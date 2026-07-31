# swapButton method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Returns the SWAP-click button number for a slot, if it is directly hotbar/offhand-addressable.

## Syntax

```java
private static int swapButton(net.minecraft.world.inventory.Slot)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| slot | Slot | The slot to check for SWAP-click addressability |

## Remarks

**Algorithm:**

1. If the slot's container is not a player `Inventory`, return -1 (not addressable).
2. Get `containerSlot` index:
   - 0-8: hotbar slots → return the containerSlot value (0-8).
   - 40: offhand slot → return 40.
   - Otherwise: return -1.

**Return value:** The SWAP button number (0-8 for hotbar, 40 for offhand), or -1 if the slot is not directly SWAP-addressable.

**Usage:** A SWAP click with this button number on another slot performs a two-way swap between the hotbar/offhand item and the clicked slot's item (works in any menu with `ContainerInput.SWAP`).

**Vanilla constraint:** SWAP clicks require the button slot to be able to receive the item. If the hotbar/offhand item can't be placed into the target slot (e.g., non-fuel into furnace fuel slot), vanilla silently rejects the swap.

## See Also

| Item | Description |
|------|-------------|
| [swapInMenu](swapInMenu.md) | Uses this to determine the swap strategy |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
