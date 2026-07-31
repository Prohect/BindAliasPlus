# resolveSlot method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Finds the actual `Slot` object in a container menu matching the given `SlotRef`.

## Syntax

```java
private static net.minecraft.screen.slot.Slot resolveSlot(net.minecraft.screen.ScreenHandler, com.github.prohect.alias.builtinAlias.SwapSlotAlias.SlotRef)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| menu | ScreenHandler | The currently open container menu (Yarn: `ScreenHandler`; Mojang: `AbstractContainerMenu`) |
| ref | SlotRef | The parsed slot reference (container or player) |

## Remarks

**Algorithm:**

- **Container SlotRef:** Indexes directly into `menu.slots` by `ref.index()`. Returns the slot if the index is in range, null otherwise.
- **Player SlotRef:** Iterates through all slots in the menu, returning the first slot where `slot.getIndex() == ref.index()` AND `slot.inventory instanceof PlayerInventory` (to ensure it's a player inventory slot, not another container's slot with the same index).

**Return value:** The matching `Slot` object, or null if not found.

**Why container check is needed:** In some menus (e.g., crafting table), multiple slots might have the same slot index from different containers. The `instanceof PlayerInventory` check ensures we match only the player's inventory slots.

## See Also

| Item | Description |
|------|-------------|
| [parseSlotRef](parseSlotRef.md) | Creates the SlotRef that this method resolves |
| [swapInMenu](swapInMenu.md) | Uses the resolved slots to perform the swap |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
