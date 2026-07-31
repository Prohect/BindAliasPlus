# clickSlot method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Performs a single slot click operation via the game's interaction manager.

## Syntax

```java
private static void clickSlot(net.minecraft.client.multiplayer.MultiPlayerGameMode, net.minecraft.world.inventory.AbstractContainerMenu, net.minecraft.world.inventory.Slot, int, net.minecraft.world.inventory.ContainerInput, net.minecraft.client.player.LocalPlayer)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| interactionManager | MultiPlayerGameMode | The game's interaction manager |
| menu | AbstractContainerMenu | The open container menu |
| slot | Slot | The slot to click |
| button | int | Button number: 0-8 for hotbar slots during SWAP, 40 for offhand, 0 for PICKUP |
| input | ContainerInput | Click type: `SWAP` or `PICKUP` |
| player | LocalPlayer | The local player |

## Remarks

**Algorithm:**

1. Call `interactionManager.handleContainerInput(menu.containerId, slot.index, button, input, player)`.

**Return value:** void.

**Side effects:** Sends a container click packet to the server with the specified parameters. The server processes the click and updates item stacks accordingly.

**Button semantics:**
- For `ContainerInput.PICKUP`: button=0 performs a standard left-click (pick up / place).
- For `ContainerInput.SWAP`: button=0-8 picks the corresponding hotbar slot, button=40 picks the offhand slot.

**Validation:** Button values are expected to be 0-8 or 40 (enforced by `swapButton()` which is the primary caller). No range validation is performed inside this method.

## See Also

| Item | Description |
|------|-------------|
| [swapButton](swapButton.md) | Provides the button parameter |
| [swapInMenu](swapInMenu.md) | Primary caller of this method |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
