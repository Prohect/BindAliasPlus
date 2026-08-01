# collect 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
public static LinkedHashMap<String, String> collect()
```

## 返回值

一个状态成员名 → JSON 片段的 `LinkedHashMap`。键按定义顺序排列以保证 JSON 输出一致。不可用的成员（如不在世界中时的 `world_name`）会被排除或具有 `null` 值。

## 备注

从当前的 `MinecraftClient.getInstance()` 实例组装完整的游戏状态快照。收集的成员包括：

- `world_name` — 维度/关卡名（如 `"overworld"`、`"the_nether"`）
- `pos` — 玩家位置，格式为 `{"x":...,"y":...,"z":...,"yaw":...,"pitch":...}`
- `health` — `{"current":...,"max":...}`
- `effects` — 活动状态效果，格式为 `{"name":...,"duration":"MM:SS",...}`
- `target` — 玩家注视的实体（`{"type":"...","name":"...","pos":...}`）
- `players` — 附近玩家及其方向信息（复用 `SoundCapture.directionOf`）
- `screen` — 当前界面名（`"inventory"`、`"crafting"`、`null` = 无界面）
- `looking_at` — 玩家注视的方块
- `selected_slot` — 当前选中的快捷栏槽位（1-9）
- `held_keys` — 当前按住的移动/动作按键
- `inventory` — 非快捷栏物品栏槽位
- `armor` — 盔甲槽位（脚、腿、胸、头）
- `offhand` — 副手槽位物品

容器和快捷栏由 `StateTracker.begin` 通过 `containerSnapshot`/`hotbarItems` 单独处理，以实现槽位级 diff。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | 调用方 |
| [containerSnapshot](containerSnapshot.md) | 容器状态提取 |
| [hotbarItems](hotbarItems.md) | 快捷栏槽位提取 |
