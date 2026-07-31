# hotbarEmptyRanges method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String hotbarEmptyRanges(ClientPlayerEntity p)
```

## Return value

Compressed empty-slot range string (e.g., `"1-3,7,9"`) for hotbar slots 1-9, or `null` if no slots are empty. Ranges of 3+ consecutive empty slots are collapsed to `"start-end"`.

## Remarks

Collects the 1-based indices of all empty hotbar slots (0-8 in inventory) and compresses them via `compressRanges`.

## See Also

| Item | Description |
|------|-------------|
| [compressRanges](compressRanges.md) | The range compression helper |
| [hotbarItems](hotbarItems.md) | The complementary non-empty slot extractor |
