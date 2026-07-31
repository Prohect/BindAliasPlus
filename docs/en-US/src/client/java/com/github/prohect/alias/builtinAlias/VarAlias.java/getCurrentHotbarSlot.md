# getCurrentHotbarSlot method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
private java.lang.Integer getCurrentHotbarSlot()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Returns the player's currently selected hotbar slot as a 1-based integer (1-9), matching the mod's user-facing convention. Internally calls `player.getInventory().getSelectedSlot()` which returns 0-8, then adds 1.

Returns `null` if the player or inventory is null (e.g., not in a world).

**Side effects**: None (reads game state).

**Callers**: `getValueFromSource()` when source is `"hotbarSlot"` or `"selectedSlot"`.

Return value: The hotbar slot number (1-9), or `null` if unavailable.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
