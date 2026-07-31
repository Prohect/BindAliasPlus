# swapSlotOffhand method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Performs a fast packet-based swap between a hotbar slot and the offhand slot, without opening any screen.

## Syntax

```java
private static void swapSlotOffhand(net.minecraft.client.multiplayer.ClientPacketListener, int)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| networkHandler | ClientPacketListener | The network handler for sending packets |
| ratherOffhand | int | The hotbar slot index (0-8, 0-based) to swap with the offhand |

## Remarks

**Algorithm:**

1. Send `ServerboundSetCarriedItemPacket(ratherOffhand)` — selects the specified hotbar slot.
2. Send `ServerboundPlayerActionPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN)` — swaps the selected hotbar item with the offhand item.

**Return value:** void.

**Side effects:** Swaps the item in the specified hotbar slot with the offhand item. This is a server-side operation — the client inventory updates when the server responds.

**Usage pattern for two hotbar slots:** The caller uses a 3-step sequence via `swapSlotOffhand`:
1. Swap slot0 with offhand
2. Swap slot1 with offhand (now slot1's item is in offhand, slot0's item is in slot1)
3. Swap slot0 with offhand (slot1's original item goes to slot0)

This avoids opening any screen and works while another container screen is open.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Main run method that calls this for hotbar-only swaps |
| [SwapHandAlias](../SwapHandAlias.java/SwapHandAlias.md) | Simple main↔offhand swap |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
