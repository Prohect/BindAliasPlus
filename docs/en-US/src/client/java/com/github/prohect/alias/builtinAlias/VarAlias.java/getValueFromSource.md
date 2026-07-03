# getValueFromSource method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
private java.lang.Number getValueFromSource(java.lang.String)
```

## Parameters

| Name     | Type     | Description                                                                                                                              |
| -------- | -------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| `source` | `String` | The source identifier — one of: `hotbarSlot`, `selectedSlot`, `itemsOfSlot0`-`itemsOfSlot9`, `pitch`, `yaw`, or a literal number string. |

## Remarks

Dispatches a source string to the appropriate getter method and returns the resulting `Number`.

**Dispatch logic** (in order):

1. `"hotbarSlot"` or `"selectedSlot"` (case-insensitive) → `getCurrentHotbarSlot()` → `Integer` (1-9)
2. Starts with `"itemsOfSlot"` (case-insensitive) → `getItemCountFromSlot(source)` → `Integer` (0-64)
3. `"pitch"` (case-insensitive) → `getPlayerPitch()` → `Double`
4. `"yaw"` (case-insensitive) → `getPlayerYaw()` → `Double`
5. Try `Integer.parseInt(source)` → `Integer`
6. Try `Double.parseDouble(source)` → `Double`
7. Otherwise logs an error and returns `null`

**Side effects**: None (reads game state via getters).

**Callers**: `run()` and `run(String, boolean)`.

Return value: The resolved `Number`, or `null` if the source is unrecognized.

## See Also

| Item                                            | Description        |
| ----------------------------------------------- | ------------------ |
| [getCurrentHotbarSlot](getCurrentHotbarSlot.md) | Hotbar slot getter |
| [getItemCountFromSlot](getItemCountFromSlot.md) | Item count getter  |
| [getPlayerPitch](getPlayerPitch.md)             | Pitch getter       |
| [getPlayerYaw](getPlayerYaw.md)                 | Yaw getter         |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
