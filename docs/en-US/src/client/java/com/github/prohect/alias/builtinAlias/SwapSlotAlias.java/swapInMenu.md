# swapInMenu method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void swapInMenu(MultiPlayerGameMode interactionManager, AbstractContainerMenu menu, Slot slot0, Slot slot1, LocalPlayer player)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `interactionManager` | `MultiPlayerGameMode` | The client's interaction manager. Used to send slot clicks to the server. |
| `menu` | `AbstractContainerMenu` | The currently open menu containing both slots. |
| `slot0` | `Slot` | First slot to swap. |
| `slot1` | `Slot` | Second slot to swap. |
| `player` | `LocalPlayer` | The local player (unused directly but passed for context). |

## Remarks

Swaps the contents of two slots within an open container menu. Uses two strategies:

**SWAP path** (preferred): If either `slot0` or `slot1` is hotbar/offhand-addressable (determined by `swapButton()`), a single `ContainerInput.SWAP` click is sent. This is a vanilla click type that exchanges the hotbar/offhand item with the clicked slot atomically.

**PICKUP path** (fallback): For two non-hotbar slots (e.g. two chest slots, or inventory slots 10–36), a guarded pickup sequence is used:
1. PICKUP from `slot0` (item moves to cursor).
2. PICKUP from `slot1` (slot0's item placed in slot1, slot1's item now on cursor).
3. If cursor still has an item: PICKUP back into `slot0` (slot0 rejected the put-back — this is a take-only slot like crafting result, furnace output, or anvil result).
4. If cursor STILL has an item: PICKUP back into `slot1` (both slots rejected — logs a warning).

This guarded sequence gracefully handles slots that accept items only in one direction (take-only result slots). The item ends up in whichever slot accepts it, and the cursor is cleared.

**SWAP path limitation**: If the hotbar/offhand item cannot be placed in the target slot (e.g. non-fuel into a furnace fuel slot), the server silently rejects the entire SWAP and neither item moves. For such cases, use an inventory slot (10–36) or an empty hotbar slot to force the PICKUP path.

## See Also

| Item | Description |
|------|-------------|
| [swapButton](swapButton.md) | Determines if a SWAP click is possible |
| [resolveSlot](resolveSlot.md) | Resolves slot references before swap |
| [clickSlot](clickSlot.md) | Sends individual click packets |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
