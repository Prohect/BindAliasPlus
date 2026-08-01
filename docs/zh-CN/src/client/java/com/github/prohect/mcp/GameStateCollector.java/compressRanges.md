# compressRanges 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String compressRanges(List<Integer> indices)
```

## 返回值

压缩后的范围字符串。单个索引以逗号分隔；3 个及以上连续索引的连续段折叠为 `"start-end"`。恰好 2 个连续索引保留为独立数字（例如 `"1,2,5-8,10"`）。

## 备注

`hotbarEmptyRanges` 与 `containerSnapshot` 的 `emptyInv` 字段的辅助方法。折叠前采用 2 索引阈值：恰好 2 个的连续段保留为独立数字以利于阅读。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [hotbarEmptyRanges](hotbarEmptyRanges.md) | 主要调用方 |
