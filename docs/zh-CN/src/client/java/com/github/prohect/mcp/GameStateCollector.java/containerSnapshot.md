# containerSnapshot 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static ContainerSnapshot containerSnapshot(MinecraftClient mc, ClientPlayerEntity p)
```

## 返回值

如果容器界面打开则返回 `ContainerSnapshot`；如果没有活动的容器界面或玩家为 null 则返回 `null`。

## 备注

提取打开的容器菜单（`HandledScreen`）的状态。`ContainerSnapshot` 记录捕获：

- `menuIdentity` — 由容器的槽位数、标题和类型得出的类哈希标识，`StateTracker` 用它检测菜单变化（触发完整状态转储）
- `items` — 槽位索引→物品描述的映射（仅非空槽位）
- `emptyInv` — 容器中玩家物品栏部分的压缩空槽位区间
- `grid` — 合成格表示（2×2 玩家、3×3 工作台、熔炉/燃料进度）

物品描述使用 `appendTooltipIfValuable`，合成格格式化使用 `gridJson`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [containerFullJson](containerFullJson.md) | 完整 JSON 格式化器 |
| [containerDiffJson](containerDiffJson.md) | diff JSON 格式化器 |
| [StateTracker.begin](StateTracker.java/begin.md) | 对快照做 diff 的调用方 |
