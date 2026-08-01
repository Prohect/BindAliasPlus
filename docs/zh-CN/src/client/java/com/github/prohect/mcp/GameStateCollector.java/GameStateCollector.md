# GameStateCollector（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
public final class com.github.prohect.mcp.GameStateCollector
```

## 静态初始化

_参见 [static-init](static-init.md)。_

## 备注

工具类，为 MCP 响应 envelope（状态信封）组装原始游戏状态快照。每次 MCP 请求由 [`StateTracker.begin`](StateTracker.java/begin.md) 调用。生成一个 `LinkedHashMap<String, String>`，把状态成员作为 JSON 片段存放——键如 `world`、`pos`、`health`、`effects`、`target`、`players`、`screen`、`looking_at`、`selected_slot`、`held_keys`、`hotbar`、`container` 等。每个值都预先格式化为 JSON 字符串（若数据不可用则为 null，例如不在世界中时）。

同时提供 `ContainerSnapshot` 记录及配套方法，用于容器/快捷栏槽位粒度的差分，供 `StateTracker` 在打开界面/菜单切换时产出完整视图，此后产出逐槽位差分。格式化辅助方法（`fmt1`、`fmt2`、`jsonEscape`）与其他 MCP 类共享。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | 调用 `collect()` 并构建 envelope 的调用方 |
| [collect](collect.md) | 主要快照方法 |
| [containerSnapshot](containerSnapshot.md) | 提取容器菜单状态 |
| [hotbarItems](hotbarItems.md) | 提取快捷栏槽位→物品映射 |
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | `players` 复用相同的方位格式化 |
