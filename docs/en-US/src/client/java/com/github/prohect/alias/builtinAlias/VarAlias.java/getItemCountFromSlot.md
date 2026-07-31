# getItemCountFromSlot method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
private java.lang.Integer getItemCountFromSlot(java.lang.String)
```

## Parameters

| Name     | Type     | Description                                                                                   |
| -------- | -------- | --------------------------------------------------------------------------------------------- |
| `source` | `String` | The source string in `itemsOfSlotN` format, where N is 0-9 (0 = offhand, 1-9 = hotbar slots). |

## Remarks

Returns the item count in the specified inventory slot. Parses the slot number from the `itemsOfSlot` prefix, validates the range, then retrieves the `ItemStack` and returns its count (0 if empty).

**Slot mapping**:

- 0 → offhand (internal inventory index 40)
- 1-9 → hotbar slots (internal inventory indices 0-8)

**Algorithm**:

1. Extract the slot number substring after `"itemsOfSlot"`.
2. Parse as integer; validate range 0-9.
3. Retrieve the `ItemStack` via `inventory.getItem(index)`.
4. Return `stack.getCount()` (0 for empty stacks).

**Side effects**: None (reads inventory state).

**Callers**: `getValueFromSource()` when source starts with `"itemsOfSlot"`.

Return value: Item count (0 to stack max), or `null` on error.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
