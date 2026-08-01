# tickDrop 方法（src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

当 `flag` 为 true 时，由 `MinecraftClientMixin` 在每个客户端刻调用。在初始延迟（与操作系统按键重复间隔一致）之后驱动持续丢弃。

## 语法

```java
public void tickDrop()
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（无）_ | | |

## 备注

1. 如果 `flag` 为 false，则立即返回（当前未按住）。
2. 递增 `ticksHeld`。如果 `ticksHeld <= INITIAL_DELAY_TICKS`（3 刻），则返回——初始延迟与原版操作系统按键重复行为一致，操作系统在开始按键重复前等待约 250ms。
3. 延迟过后，检查当前界面：
   - **容器界面：** 如果悬停槽位有物品，则调用 `onMouseClick(hoveredSlot, hoveredSlot.id, button, SlotActionType.THROW)`，按钮为 1（按住 Ctrl）或 0（单个物品）。
   - **3D 游戏（screen == null）：** 递增 `mc.options.dropKey.timesPressed++` 以触发另一次丢弃事件。
   - **其他界面（非容器、非 null）：** 无动作——按键丢弃只在容器界面和 3D 世界中有意义。

`MinecraftClientMixin` 在每个客户端刻无条件调用 `tickDrop()`，DropAlias 实例通过静态别名注册表获取。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 初始按下/松开处理 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 每个客户端刻调用 `tickDrop()` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
