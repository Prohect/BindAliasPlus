# parseSlotRef method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Parses a single slot argument string into a `SlotRef` — either a player inventory slot index (0-40) or a container slot index.

## Syntax

```java
private static com.github.prohect.alias.builtinAlias.SwapSlotAlias.SlotRef parseSlotRef(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| arg | String | Slot argument: `"cN"` for container slot, a number 1-41 for player slot, or a variable name |

## Remarks

**Algorithm:**

1. Trim the argument string.
2. If the string starts with 'c' and has length > 1: parse the rest as an integer N. If N >= 1, return a container SlotRef with index `N-1` (0-based).
3. Check `VarAlias.CONTAINER_SLOT_VARIABLES` for the trimmed name — variables created with a `cN` source are stored here. If found, return a container SlotRef with `value-1`.
4. Call `VarAlias.resolveInt(trimmed)` to resolve as a number or variable. If null, return null (invalid).
5. Convert to 0-based index (`resolved - 1`). If in range [0, 40], return a player SlotRef. Otherwise, return null.

**Return value:** A `SlotRef` record (container=true/false, index=0-based), or null if the argument is invalid.

**Edge cases:**
- cN values are always treated as container slots, even if a variable with the same name exists.
- Player slot numbers are validated to be in range 1-41.

## See Also

| Item | Description |
|------|-------------|
| [VarAlias](../VarAlias.java/CONTAINER_SLOT_VARIABLES.md) | Container slot variable storage |
| [VarAlias](../VarAlias.java/resolveInt.md) | Integer resolution for player slots |
| [resolveSlot](resolveSlot.md) | Find Slot object from SlotRef in menu |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
