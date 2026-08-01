# AbstractContainerScreenMixin（src/client/java/com/github/prohect/mixin/client/AbstractContainerScreenMixin.java）

## 语法

```java
@Mixin(AbstractContainerScreen.class)
public abstract class com.github.prohect.mixin.client.AbstractContainerScreenMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.gui.screens.inventory.AbstractContainerScreen`，在 [`FreeCursorAlias.freeCursor`](../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) 生效时将悬停槽位固定到代理的槽位 14（玩家物品栏 containerSlot 13）。原版每帧通过 `getHoveredSlot` 根据 OS 鼠标位置重新计算 `hoveredSlot`；覆盖这一个方法使 `+drop` 和 `swapSlot` 操作无论宿主光标停在哪里都确定性地瞄准槽位 14。freeCursor 关闭时，悬停行为不修改。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias](../../alias/builtinAlias/FreeCursorAlias.java/README.md) | 门控此行为的 `freeCursor` 标志的来源 |
| [pinHoveredSlotTo14](pinHoveredSlotTo14.md) | 覆盖 `getHoveredSlot` 的 `@Inject` |
