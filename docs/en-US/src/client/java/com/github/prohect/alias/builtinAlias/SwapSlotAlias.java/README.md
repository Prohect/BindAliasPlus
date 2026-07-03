# SwapSlotAlias

## Fields

| Name     | Type | Description                             |
| -------- | ---- | --------------------------------------- |
| _(none)_ |      | No fields declared beyond parent class. |

## Methods

| Name                                  | Signature                                                                                     | Description                                                 |
| ------------------------------------- | --------------------------------------------------------------------------------------------- | ----------------------------------------------------------- |
| [run](run.md)                         | `public SwapSlotAlias run(String args)`                                                       | Parses slot arguments and performs the swap                 |
| [clickSlot](clickSlot.md)             | `private static void clickSlot(MultiPlayerGameMode, InventoryScreen, Slot, int, LocalPlayer)` | Simulates a slot click via the interaction manager          |
| [swapSlotOffhand](swapSlotOffhand.md) | `private static void swapSlotOffhand(ClientPacketListener, int)`                              | Swaps an item with the offhand via network packets          |
| [getSlot](getSlot.md)                 | `private static Slot getSlot(InventoryScreen, int)`                                           | Finds a slot by its container index in the inventory screen |

## See Also

| Item                                     | Description                            |
| ---------------------------------------- | -------------------------------------- |
| [VarAlias](../VarAlias.java/README.md)   | Variable resolution for slot arguments |
| [SlotAlias](../SlotAlias.java/README.md) | Simple hotbar slot selection           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
