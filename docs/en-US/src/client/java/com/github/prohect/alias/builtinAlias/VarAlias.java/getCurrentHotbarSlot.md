# getCurrentHotbarSlot method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Returns the currently selected hotbar slot number (1-9).

## Syntax

```java
private java.lang.Integer getCurrentHotbarSlot()
```

## Remarks

**Algorithm:**

1. Get `mc.player` and `player.getInventory()`.
2. If either is null, log a warning and return null.
3. Call `inventory.getSelectedSlot()` which returns 0-8.
4. Add 1 to convert to the mod's 1-9 convention.

**Return value:** Integer 1-9, or null if player/inventory is unavailable.

**Convention:** Vanilla `getSelectedSlot()` returns 0-based (0-8). This method adds 1 to match the `slot\N` command convention (1-9).

**Error handling:** Logs `"[var] Player is null"` or `"[var] Inventory is null"` if unavailable.

## See Also

| Item | Description |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | Primary caller (for `"hotbarSlot"` source) |
| [SlotAlias](../SlotAlias.java/run.md) | Direct hotbar slot selection |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
