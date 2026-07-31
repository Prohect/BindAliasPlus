# swapOff method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void swapOff(ClientPlayNetworkHandler net, int idx)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `net` | `ClientPlayNetworkHandler` | The network handler used to send packets |
| `idx` | `int` | 0-based hotbar slot index to swap with the offhand |

## Remarks

Private static helper that performs a hotbar↔offhand swap using two vanilla packets:

1. **`UpdateSelectedSlotC2SPacket(idx)`** — temporarily selects hotbar slot `idx` (0-based, corresponding to hotbar slots 1–9).
2. **`PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN)`** — sends the offhand swap action, which exchanges the currently selected hotbar item with the offhand item.

This is a packet-level operation that works without any screen being open, relying only on the network handler. It does not require a container menu or interaction manager, so it can execute even while another container screen is open.

Used by `SwapSlotAlias.run()` in the "inside" swap strategy: when both slots are hotbar (0–8) or offhand (40), the method uses a sequence of up to three `swapOff` calls to achieve a 3-way offhand rotation that exchanges the two target slots without disrupting the offhand slot's final content. After all `swapOff` calls, the selected hotbar slot is restored to its original value via another `UpdateSelectedSlotC2SPacket`.

The 26.x (Mojang) equivalent was called `swapSlotOffhand` and sent the same two packet types — the rename aligns with the Yarn mapping convention where the action constant is `SWAP_ITEM_WITH_OFFHAND`.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | The caller — uses swapOff in the hotbar/offhand swap strategy |
| [SwapSlotAlias](SwapSlotAlias.md) | The enclosing class |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
