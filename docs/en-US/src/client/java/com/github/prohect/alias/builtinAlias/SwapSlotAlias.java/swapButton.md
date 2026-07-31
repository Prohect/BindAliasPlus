# swapButton method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static int swapButton(Slot slot)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `slot` | `Slot` | A slot from the current menu. Checked for SWAP-click addressability. |

## Remarks

Determines the button index for a vanilla `SWAP` click on a slot. Only player inventory slots can be swap-addressable:

- **Hotbar slots** (`containerSlot` 0–8): Returns the hotbar index (same value). A SWAP click with this button on any other slot swaps those two items.
- **Offhand slot** (`containerSlot` 40): Returns 40.
- **Non-hotbar inventory slots** (9–36) and **container slots**: Returns `-1` — not directly swap-addressable. The caller falls through to the PICKUP-based swap path.

A return of `-1` means `swapInMenu` must use the PICKUP click sequence instead of SWAP.

## See Also

| Item | Description |
|------|-------------|
| [swapInMenu](swapInMenu.md) | Uses the button index for SWAP-clicks |
| [resolveSlot](resolveSlot.md) | Resolves the slot before determining its button |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
