# clickSlot method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void clickSlot(net.minecraft.client.multiplayer.MultiPlayerGameMode, net.minecraft.client.gui.screens.inventory.InventoryScreen, net.minecraft.world.inventory.Slot, int, net.minecraft.client.player.LocalPlayer)
```

## Parameters

| Name                 | Type                  | Description                                                                                          |
| -------------------- | --------------------- | ---------------------------------------------------------------------------------------------------- |
| `interactionManager` | `MultiPlayerGameMode` | The client's interaction manager for handling container input.                                       |
| `inventoryScreen`    | `InventoryScreen`     | The open inventory screen whose container is being manipulated.                                      |
| `slot`               | `Slot`                | The slot to click on in the inventory screen.                                                        |
| `button`             | `int`                 | The button index for the click — 0-8 for hotbar slots, or 40 for offhand. Only 0-8 and 40 are valid. |
| `player`             | `LocalPlayer`         | The local player.                                                                                    |

## Remarks

Simulates a slot click in an open inventory screen by calling `interactionManager.handleContainerInput()` with `ContainerInput.SWAP`. This triggers the same server-bound packet that a normal mouse click would generate, swapping the clicked slot's item with the hotbar/offhand slot specified by `button`.

**Algorithm**: Single call to `interactionManager.handleContainerInput(inventoryScreen.menu.containerId, slot.index, button, ContainerInput.SWAP, player)`.

**Side effects**: Sends a container click packet to the server. Modifies the container state on both client and server.

**Callers**: `run()` — called from within the inventory screen manipulation block.

**Error handling**: No internal validation beyond what `handleContainerInput` provides. The caller is responsible for ensuring `button` is valid and the screen/player are not null.

## See Also

| Item          | Description                         |
| ------------- | ----------------------------------- |
| [run](run.md) | Caller — orchestrates slot swapping |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
