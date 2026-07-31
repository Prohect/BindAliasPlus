# swapSlotOffhand method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void swapSlotOffhand(net.minecraft.client.multiplayer.ClientPacketListener, int)
```

## Parameters

| Name             | Type                   | Description                                                  |
| ---------------- | ---------------------- | ------------------------------------------------------------ |
| `networkHandler` | `ClientPacketListener` | The client's network connection for sending packets.         |
| `ratherOffhand`  | `int`                  | The inventory slot index (0-based) to swap with the offhand. |

## Remarks

Swaps a hotbar or inventory slot with the offhand slot using direct network packets, bypassing the need to open the inventory screen.

**Algorithm**:

1. Send `ServerboundSetCarriedItemPacket(ratherOffhand)` — sets the carried/hotbar item to the specified slot.
2. Send `ServerboundPlayerActionPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN)` — performs the actual offhand swap.

This is the same packet sequence vanilla Minecraft uses when the player presses the offhand swap key (`F` by default).

**Side effects**: Sends two network packets. Modifies the player's offhand and hotbar state on the server.

**Callers**: `run()` — used for the fast path when both slots involved are in the hotbar/offhand range.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
