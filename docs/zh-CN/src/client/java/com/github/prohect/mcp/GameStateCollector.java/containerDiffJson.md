# containerDiffJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String containerDiffJson(ContainerSnapshot last, ContainerSnapshot cur)
```

## 返回值

只包含发生变化槽位的差分 JSON 对象字符串。值为 `null` 的条目表示该槽位变空。`empty_inv` 与 `grid` 仅在发生变化时包含。无变化时返回 `null`。

## 备注

计算两个容器快照之间的槽位级差分：

- **新增/变化的槽位**：存在于 `cur.items` 中，但在 `last.items` 中缺失或值不同的槽位。
- **移除的槽位**：存在于 `last.items` 中但不在 `cur.items` 中的槽位→值为 `null`。
- **empty_inv**：仅当空范围字符串不同时包含。
- **grid**：仅当合成格表示不同时包含。

当所有跟踪字段都相同时返回 `null`，通知 `StateTracker` 从 envelope 中省略 `container` 成员。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [containerFullJson](containerFullJson.md) | 完整变体 |
| [StateTracker.begin](StateTracker.java/begin.md) | 调用方 |
