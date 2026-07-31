# containerDiffJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String containerDiffJson(ContainerSnapshot last, ContainerSnapshot cur)
```

## Return value

Diff JSON object string containing only changed slots. An item with value `null` means the slot became empty. Includes `empty_inv` and `grid` only when they changed. Returns `null` when nothing changed.

## Remarks

Computes a slot-level diff between two container snapshots:

- **Added/changed slots**: slots present in `cur.items` but missing or with a different value in `last.items`.
- **Removed slots**: slots present in `last.items` but absent in `cur.items` → value is `null`.
- **empty_inv**: included only when the empty-range string differs.
- **grid**: included only when the grid representation differs.

Returns `null` when all tracked fields are identical, signaling `StateTracker` to omit the `container` member from the envelope.

## See Also

| Item | Description |
|------|-------------|
| [containerFullJson](containerFullJson.md) | The full variant |
| [StateTracker.begin](StateTracker.java/begin.md) | The caller |
