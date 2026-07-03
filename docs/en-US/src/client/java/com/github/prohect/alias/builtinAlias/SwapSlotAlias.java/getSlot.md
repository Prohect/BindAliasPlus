# getSlot method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static net.minecraft.world.inventory.Slot getSlot(net.minecraft.client.gui.screens.inventory.InventoryScreen, int)
```

## Parameters

| Name              | Type              | Description                                                            |
| ----------------- | ----------------- | ---------------------------------------------------------------------- |
| `inventoryScreen` | `InventoryScreen` | The open inventory screen whose container slots are searched.          |
| `index`           | `int`             | The container slot index to find (0-based, 0-40 for player inventory). |

## Remarks

Finds a `Slot` in the inventory screen's container that matches the given container index and belongs to a player `Inventory` container. Iterates over all slots in `inventoryScreen.menu.slots` and returns the first match.

**Algorithm**: Linear scan of `inventoryScreen.menu.slots`. For each slot, check `slot.getContainerSlot() == index && slot.container instanceof Inventory`.

**Side effects**: None (pure lookup).

**Callers**: `run()` — used to obtain `Slot` references for `clickSlot()` calls.

Return value: The matching `Slot`, or `null` if no slot with the given index is found or if the slot's container is not a player `Inventory`.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
