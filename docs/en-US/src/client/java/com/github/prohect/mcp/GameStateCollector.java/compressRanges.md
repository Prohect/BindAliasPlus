# compressRanges method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String compressRanges(List<Integer> indices)
```

## Return value

Compressed range string. Single indices are comma-separated; runs of 3+ consecutive indices are collapsed to `"start-end"`. Two consecutive indices remain as individual numbers (e.g., `"1,2,5-8,10"`).

## Remarks

Helper for `hotbarEmptyRanges` and `containerSnapshot`'s `emptyInv` field. Uses a 2-index threshold before collapsing: runs of exactly 2 are kept as separate numbers for readability.

## See Also

| Item | Description |
|------|-------------|
| [hotbarEmptyRanges](hotbarEmptyRanges.md) | The primary caller |
