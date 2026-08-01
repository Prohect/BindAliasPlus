# HandledScreenMixin（src/client/java/com/github/prohect/mixin/client/HandledScreenMixin.java）

## 语法

```java
@Mixin(HandledScreen.class)
public abstract class com.github.prohect.mixin.client.HandledScreenMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.gui.screen.ingame.HandledScreen`，在 `FreeCursorAlias.freeCursor` 激活时覆盖悬停槽位计算。原版每帧根据 OS 鼠标位置重新计算 `focusedSlot`（`renderMain` → `getSlotAt`）；通过在 `getSlotAt(double, double)` 的 `RETURN` 处注入并替换返回值为 `index` 等于 `FORCED_HOVER_INDEX`（13，0 基——映射到容器槽位 14）的玩家物品栏槽位，自由的宿主光标对悬停变得无关紧要。这使得 `+drop` 和交换操作确定性地针对槽位 14，无论 OS 光标位于何处。当 `freeCursor` 关闭（正常的锁定光标游玩）时，注入提前返回，悬停行为不受影响。

常量 `FORCED_HOVER_INDEX = 13` 的选择依据：容器自身槽位之后的容器槽位通常是玩家物品栏区域，而索引 13（第 14 个 0 基索引）在大多数容器界面布局中对应一个可预测的"agent 槽位"。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [pinFocusedSlotTo14](pinFocusedSlotTo14.md) | 替换悬停槽位的 `@Inject` |
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 把关此注入的标志 |
| [MouseMixin](../MouseMixin.java/README.md) | freeCursor 期间抑制 OS 光标锁定 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
