# begin 方法（src/client/java/com/github/prohect/mcp/StateTracker.java）

## 语法

```java
public static synchronized String begin(boolean full)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `full` | `boolean` | `true` 强制包含所有状态成员（用于 `/state`），`false` 仅包含变更成员的 diff |

## 返回值

JSON envelope 的开头：`{"client_tick":N[,"state":{...}]}`。调用方必须将其传给 `finish()` 以完成 envelope。

## 备注

必须在 Minecraft 主线程上调用（访问 `MinecraftClient.getInstance()`）。通过 `synchronized` 保证线程安全。

1. **世界变更检测**：如果 `BindAliasClient.joinTick` 与存储的基线不同，则强制 `full = true` 并重置上一个快照。
2. **状态收集**：调用 `GameStateCollector.collect()` 获取当前快照。
3. **成员级 diff**：对每个当前状态成员，若 `full` 或值与上一个快照不同则包含。`held_keys` 非空时强制包含。
4. **消失的成员**：上一个快照中存在但当前快照中缺失的任何键序列化为 `null`。
5. **容器 diff**：调用 `containerSnapshot()`，与 `lastContainer` 比较。`full` / 打开 / 菜单身份变化时给完整视图；否则给槽位级 diff。
6. **快捷栏 diff**：相同模式——`full` / 世界变化时给完整视图；否则逐槽位 diff。
7. **基线更新**：将当前快照存为 `last`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [finish](finish.md) | 第二阶段 |
| [reset](reset.md) | 手动基线重置 |
| [GameStateCollector.collect](GameStateCollector.java/collect.md) | 原始状态收集 |
| [GameStateCollector.containerSnapshot](GameStateCollector.java/containerSnapshot.md) | 容器状态提取 |
| [GameStateCollector.hotbarItems](GameStateCollector.java/hotbarItems.md) | 快捷栏状态提取 |
