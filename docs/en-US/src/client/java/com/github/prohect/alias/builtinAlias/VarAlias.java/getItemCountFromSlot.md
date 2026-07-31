# getItemCountFromSlot method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Returns the item count from a specified slot using the `"itemsOfSlotN"` source pattern.

## Syntax

```java
private java.lang.Integer getItemCountFromSlot(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| source | String | Source string like `"itemsOfSlot0"`, `"itemsOfSlot5"`, etc. |

## Remarks

**Algorithm:**

1. Get `mc.player` and `player.getInventory()` — return null if unavailable.
2. Extract the slot number from the source by stripping the `"itemsOfSlot"` prefix.
3. Parse the remaining string as an integer `slotIndex`.
4. Validate `slotIndex` is in range [0, 9].
   - 0 = offhand (internal inventory index 40).
   - 1-9 = hotbar slots (internal inventory indices 0-8).
5. Get the `ItemStack` at the corresponding inventory index.
6. Return `stack.isEmpty() ? 0 : stack.getCount()`.

**Return value:** The item count (0 if empty/slot not found), or null if player/inventory is unavailable or the slot number is invalid.

**Slot mapping:**

| Source | Inventory Index |
|--------|----------------|
| `itemsOfSlot0` | 40 (offhand) |
| `itemsOfSlot1` | 0 (hotbar 1) |
| ... | ... |
| `itemsOfSlot9` | 8 (hotbar 9) |

**Error handling:**
- Invalid slot number: logs error with valid range hint.
- Player null: logs warning.
- Inventory null: logs warning.
- Number format error: logs error.

## See Also

| Item | Description |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | Primary caller (for `"itemsOfSlotN"` source) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
