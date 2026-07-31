# getValueFromSource method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Resolves a source string to a numeric value by checking each known source type.

## Syntax

```java
private java.lang.Number getValueFromSource(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| source | String | The source identifier: `"hotbarSlot"`, `"itemsOfSlotN"`, `"pitch"`, `"yaw"`, `"cN"`, or a literal number |

## Remarks

**Algorithm (evaluated in order):**

1. **`hotbarSlot` / `selectedSlot`:** Return current hotbar slot (1-9) via `getCurrentHotbarSlot()`.
2. **`itemsOfSlotN`:** If starts with `"itemsOfSlot"` (case-insensitive), parse the trailing number (0-9) and return item count via `getItemCountFromSlot()`.
3. **`pitch`:** Return player pitch via `getPlayerPitch()`.
4. **`yaw`:** Return player yaw via `getPlayerYaw()`.
5. **`cN`:** Parse via `fromContainerSlotSource()`. If valid, return the integer N.
6. **Literal number:** Try `Integer.parseInt(source)`, then `Double.parseDouble(source)`. If both fail, log error and return null.

**Return value:** A `Number` (Integer or Double) if successful, or null if the source is unrecognized or player/inventory is unavailable.

**Error handling:** Unknown sources log an error with the list of valid source types. Player/inventory null return null (no log — handled by `run()` which checks for null).

## See Also

| Item | Description |
|------|-------------|
| [getCurrentHotbarSlot](getCurrentHotbarSlot.md) | Hotbar slot resolver |
| [getItemCountFromSlot](getItemCountFromSlot.md) | Item count resolver |
| [getPlayerPitch](getPlayerPitch.md) | Pitch resolver |
| [getPlayerYaw](getPlayerYaw.md) | Yaw resolver |
| [fromContainerSlotSource](fromContainerSlotSource.md) | cN parser |
| [run](run.md) | Primary caller |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
