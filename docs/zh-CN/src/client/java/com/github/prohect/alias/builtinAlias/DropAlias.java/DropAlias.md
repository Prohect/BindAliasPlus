# DropAlias (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

模拟丢弃物品按键绑定（Q 键）的内置别名。支持带初始延迟的持续丢弃（匹配操作系统按键重复行为），并对容器界面有特殊处理。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.DropAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DropAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinDrop"`。用法：`+drop` 按下，`-drop` 松开。DropAlias 比其他开关别名更复杂，因为它既要处理立即的首次丢弃，也要处理延迟后的持续丢弃。

**首次丢弃：** 按下（`+drop`）时执行一次立即丢弃——容器界面中用 `slotClicked(…, THROW)`，3D 游戏中用 `keyDrop.clickCount++`。

**持续丢弃（`tickDrop()`）：** 由 `MinecraftClientMixin` 在 `flag` 为 true 期间每个客户端刻驱动。经过 `INITIAL_DELAY_TICKS`（3 刻，匹配操作系统按键重复间隔）的初始延迟后，每刻触发一次丢弃动作。

**容器界面特殊行为：** 容器界面（箱子、熔炉、物品栏）打开时，丢弃作用于悬停的槽位，使用带 `ContainerInput.THROW` 的 `slotClicked()`。Ctrl+点击检测（`hasControlDown()`）决定丢弃整个堆叠（按钮 1）还是单个物品（按钮 0）。

**重新应用：** 界面切换后，`reapplyToGameKeyMapping()` 设置 `keyDrop.setDown(true)`，但**不**递增 `clickCount`——这防止光标重新锁定时触发额外丢弃。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `INITIAL_DELAY_TICKS` | `static final int` | 按下后开始持续丢弃前等待的刻数（3 刻，匹配操作系统按键重复） |
| `ticksHeld` | `private long` | 自上次按下以来经过的刻数；松开时重置为 0 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tickDrop](tickDrop.md) | 逐刻持续丢弃驱动器 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | 无额外丢弃的重新应用 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 每个客户端刻调用 `tickDrop()` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
