# DropAlias (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

模拟丢弃物品按键绑定（Q 键）的内置别名。支持带初始延迟（与操作系统按键重复行为一致）的持续丢弃，以及对容器界面的特殊处理。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.DropAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DropAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinDrop"`。用法：`+drop` 按下，`-drop` 松开。DropAlias 比其他开关别名更复杂，因为它既要处理立即的首次丢弃，也要处理延迟后的持续丢弃。

**首次丢弃：** 按下（`+drop`）时立即执行一次丢弃——在容器界面中为 `onMouseClick(…, THROW)`，在 3D 游戏中为 `dropKey.timesPressed++`。

**持续丢弃（`tickDrop()`）：** 当 `flag` 为 true 时，由 `MinecraftClientMixin` 在每个客户端刻驱动。在 `INITIAL_DELAY_TICKS`（3 刻，与操作系统按键重复间隔一致）的初始延迟之后，每刻触发一次丢弃动作。

**容器界面特殊行为：** 容器界面（箱子、熔炉、物品栏）打开时，丢弃以悬停槽位为目标，使用 `onMouseClick()` 配合 `SlotActionType.THROW`。Ctrl+点击检测（`hasControlDown()`）控制是丢弃整个堆叠（按钮 1）还是单个物品（按钮 0）。

**重新应用：** 界面切换后，`reapplyToGameKeyMapping()` 设置 `dropKey.setPressed(true)`，但**不**递增 `timesPressed`——这防止光标重新锁定时触发多余的丢弃。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `INITIAL_DELAY_TICKS` | `static final int` | 按下后等待持续丢弃开始的刻数（3 刻，与操作系统按键重复一致） |
| `ticksHeld` | `private long` | 自上次按下以来经过的刻数；松开时重置为 0 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tickDrop](tickDrop.md) | 每刻持续丢弃驱动器 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | 不产生额外丢弃的重新应用 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 每个客户端刻调用 `tickDrop()` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
