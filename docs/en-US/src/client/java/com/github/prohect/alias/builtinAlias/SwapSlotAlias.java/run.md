# run method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                                                                                                                                                   |
| ------ | -------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Slot indices separated by `Alias.divider4AliasArgs`. Accepts 1 or 2 arguments. Values 1-9 = hotbar, 10-36 = inventory, 37-40 = equipment (37=feet, 40=head), 41 = offhand. Also supports variable names resolved via `VarAlias.resolveInt()`. |

## Remarks

Swaps items between two inventory slots. With one argument, swaps the specified slot with the currently selected hotbar slot. With two arguments, swaps those two slots directly.

**Algorithm** (simplified):

1. **Parse args**: Split by alias argument divider. Resolve each token via `VarAlias.resolveInt()`. Convert 1-based user input to 0-based internal indices.
2. **Validation**: Guard against null player/inventory/network handler. Reject out-of-range (<0 or >40) indices and equal indices.
3. **Creative inventory**: Close creative inventory screen before manipulation (it interferes with slot indices).
4. **Fast path — both slots in hotbar/offhand**: Use `swapSlotOffhand()` network packets for direct offhand swaps without opening the inventory screen. For two hotbar-only slots, perform a three-way swap via offhand.
5. **Slow path — inventory screen required**: Open (or reuse) an `InventoryScreen`, then call `clickSlot()` via the interaction manager. Special-cases offhand and hotbar slots for efficiency. Closes the screen in a `finally` block if it wasn't already open.
6. **Error handling**: All validation failures log a warning and return early. The entire swap body is wrapped in a try-catch that logs errors.

**Side effects**: May open/close the inventory screen, send network packets, modify the player's inventory state. Skips execution if any non-inventory screen is open and inventory access is needed.

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                                  | Description                     |
| ----------------------------------------------------- | ------------------------------- |
| [VarAlias.resolveInt](../VarAlias.java/resolveInt.md) | Slot argument resolution        |
| [clickSlot](clickSlot.md)                             | Screen-based slot clicking      |
| [swapSlotOffhand](swapSlotOffhand.md)                 | Network-based offhand swap      |
| [getSlot](getSlot.md)                                 | Slot lookup in inventory screen |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
