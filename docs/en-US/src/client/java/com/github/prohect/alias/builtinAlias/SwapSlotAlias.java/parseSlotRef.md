# parseSlotRef method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static SlotRef parseSlotRef(String arg)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `arg` | `String` | A single slot argument token. Either `cN` (e.g. `c1`, `c5`) for a container slot, or a plain number / variable name resolving to 1–41 for a player inventory slot. |

## Remarks

Parses a user-provided slot reference into a `SlotRef` record. Two formats are supported:

- **Container slot**: Matches the pattern `^[cC](\d+)$`. The number is 1-based; the resulting `SlotRef` stores a 0-based index. Returns `null` if the number is less than 1 or unparseable.
- **Player inventory slot**: Resolves via `VarAlias.resolveInt(trimmed)` — supports both plain integer strings and variable names registered with `/alias var`. The resolved value is 1-based; converted to 0-based index. Valid range is 0–40 (1–41 user-facing). Returns `null` if resolution fails or index is out of range.

Called by `run()` for each argument token. A `null` return causes the swap to abort with a warning log.

## See Also

| Item | Description |
|------|-------------|
| [resolveSlot](resolveSlot.md) | Resolves a `SlotRef` to a `Slot` object in a menu |
| [VarAlias.resolveInt](../VarAlias.java/resolveInt.md) | Variable resolution for player inventory slots |
| [run](run.md) | Entry point that calls this parser |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
