# swapOff method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void swapOff(ClientPlayNetworkHandler net, int idx)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `net` | `ClientPlayNetworkHandler` | The client's network handler. Used to send packets directly to the server. |
| `idx` | `int` | The hotbar slot index (0–8) whose item will be swapped into the offhand. The currently selected slot is NOT restored — the caller must send a `UpdateSelectedSlotC2SPacket` to restore it afterward. |

## Remarks

Swaps the item in hotbar slot `idx` with the offhand item by sending two packets directly to the server:

1. **`UpdateSelectedSlotC2SPacket(idx)`** — Selects hotbar slot `idx` so the server knows which slot to act on.
2. **`PlayerActionC2SPacket(Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN)`** — Performs the offhand swap. `BlockPos.ORIGIN` and `Direction.DOWN` are sent as dummy values because the server ignores position and direction for this action.

This is a **packet-level offhand swap**, not a slot click. It does not require an open container screen — it works from the inventory or even without any screen open.

**Caller behavior** (in `run()`): After the swap sequence completes, the caller sends `UpdateSelectedSlotC2SPacket(sel)` to restore the originally selected hotbar slot. This prevents the hotbar selection from drifting due to the intermediate `idx` selection.

**Usage patterns in `run()`**:
- **One slot is offhand, the other is hotbar**: Calls `swapOff` once to swap the hotbar item into the offhand. The offhand's previous item ends up in the hotbar slot.
- **Both are hotbar slots**: Calls `swapOff` three times — swap slot0 → offhand, swap slot1 → offhand (which exchanges offhand with slot1, slot0's old item stays in offhand, slot1's old item goes to offhand, offhand's old item goes to slot1), then swap slot0 → offhand (offhand now has slot1's old item in slot0). This three-step shuffle exchanges two hotbar slots via the offhand as a temporary holding slot.
- **Both are hotbar and one is offhand**: Handled the same as the single-offhand case, with the offhand as one of the two slots.

> **Mojang mapping note**: In 1.21.9+ (Mojang-mapped branches), the equivalent is `swapSlotOffhand()` with `ClientPacketListener` and `int`. In 1.21.8 (Yarn), it is `swapOff()` with `ClientPlayNetworkHandler`. The method `sendPacket()` here corresponds to `send()` in Mojang mappings.

## See Also

| Item | Description |
|------|-------------|
| [swapInMenu](swapInMenu.md) | The menu-based swap path. Uses `click()` for container-screen swaps; `swapOff` handles inventory-only swaps. |
| [click](click.md) | Sends slot-click packets (used by `swapInMenu`). Contrast with `swapOff` which uses direct packet sends. |
| [run](run.md) | The entry point that decides between `swapOff` (inventory) and `swapInMenu` (container screen) paths. |

*Documented for Commit: [ef1c450870a32bbba509f486207fd6b144527f15](https://github.com/Prohect/BindAlias/tree/ef1c450870a32bbba509f486207fd6b144527f15)*
