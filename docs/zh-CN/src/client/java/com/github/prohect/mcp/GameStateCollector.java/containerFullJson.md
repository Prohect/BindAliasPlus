# containerFullJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String containerFullJson(ContainerSnapshot snap)
```

## 返回值

容器的完整 JSON 对象字符串，包含 `items`（所有非空槽位）、`empty_inv`（压缩的空范围）与可选的 `grid`。

## 备注

将 `ContainerSnapshot` 格式化为完整 JSON 对象。由 `StateTracker.begin` 在首个快照、世界变化及容器菜单身份变化时使用。与 `containerDiffJson` 不同，它包含每一个非空槽位。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [containerDiffJson](containerDiffJson.md) | 增量差分变体 |
| [StateTracker.begin](StateTracker.java/begin.md) | 调用方 |
