# resolveSlot method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static Slot resolveSlot(AbstractContainerMenu menu, SlotRef ref)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `menu` | `AbstractContainerMenu` | The currently open container menu (inventory screen or container screen). Supplies the slot list to search. |
| `ref` | `SlotRef` | Parsed slot reference indicating whether to search by container index or player inventory `containerSlot`. |

## Remarks

Resolves a `SlotRef` to the actual `Slot` object in the given menu.

- **Container slots** (`ref.container() == true`): Directly indexes `menu.slots.get(ref.index())`. The index must be within bounds.
- **Player inventory slots**: Iterates all slots in the menu, looking for one whose `getContainerSlot()` matches `ref.index()` AND whose backing container is a player `Inventory`. This filters out container slots that happen to share the same slot number.

Returns `null` if no matching slot is found (invalid index or slot not present in current menu). The caller logs a warning and aborts the swap.

## See Also

| Item | Description |
|------|-------------|
| [parseSlotRef](parseSlotRef.md) | Parses the raw argument into a `SlotRef` |
| [swapInMenu](swapInMenu.md) | Uses the resolved slots to perform the swap |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
