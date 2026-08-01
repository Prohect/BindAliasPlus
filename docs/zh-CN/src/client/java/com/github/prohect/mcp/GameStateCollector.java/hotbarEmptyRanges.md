# hotbarEmptyRanges 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String hotbarEmptyRanges(LocalPlayer p)
```

## 返回值

快捷栏槽位 1-9 的压缩空槽位区间字符串（例如 `"1-3,7,9"`），若无空槽位则为 `null`。3 个及以上连续空槽位的区间折叠为 `"start-end"`。

## 备注

收集所有空快捷栏槽位（物品栏中为 0-8）的 1 基索引，并通过 `compressRanges` 压缩。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [compressRanges](compressRanges.md) | 区间压缩辅助方法 |
| [hotbarItems](hotbarItems.md) | 互补的非空槽位提取器 |
