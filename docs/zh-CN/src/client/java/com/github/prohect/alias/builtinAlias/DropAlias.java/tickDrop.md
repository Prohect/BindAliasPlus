# tickDrop 方法（src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java）

`flag` 为 true 期间由 `MinecraftClientMixin` 在每个客户端刻调用。在匹配操作系统按键重复间隔的初始延迟后驱动持续丢弃。

## 语法

```java
public void tickDrop()
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（无）_ | | |

## 备注

1. 若 `flag` 为 false，立即返回（当前未按住）。
2. 递增 `ticksHeld`。若 `ticksHeld <= INITIAL_DELAY_TICKS`（3 刻），返回——初始延迟匹配原版的操作系统按键重复行为，操作系统在开始按键重复前等待约 250ms。
3. 延迟过后，检查当前界面：
   - **容器界面：** 若悬停的槽位有物品，调用 `slotClicked(hoveredSlot, hoveredSlot.index, button, ContainerInput.THROW)`，按钮为 1（按住 Ctrl）或 0（单个物品）。
   - **3D 游戏（界面为 null）：** 递增 `mc.options.keyDrop.clickCount++` 以触发下一次丢弃事件。
   - **其他界面（非容器、非 null）：** 不执行操作——按键丢弃只在容器界面和 3D 世界中有意义。

`MinecraftClientMixin` 在每个客户端刻无条件调用 `tickDrop()`，DropAlias 实例通过静态别名注册表获取。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 初始按下/松开处理器 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 每个客户端刻调用 `tickDrop()` |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
