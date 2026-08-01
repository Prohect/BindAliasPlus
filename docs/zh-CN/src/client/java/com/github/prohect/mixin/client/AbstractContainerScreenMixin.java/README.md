# AbstractContainerScreenMixin

针对 `net.minecraft.client.gui.screens.inventory.AbstractContainerScreen` 的 mixin。freeCursor 生效时将悬停槽位固定到槽位 14，使代理的丢弃/交换操作无论宿主光标位置如何都始终瞄准确定性的槽位。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `FORCED_HOVER_CONTAINER_SLOT` | `int`（静态，私有，`13`） | 强制悬停的 0 基 containerSlot 索引（玩家物品栏槽位 14） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [pinHoveredSlotTo14](pinHoveredSlotTo14.md) | `void pinHoveredSlotTo14(double x, double y, CallbackInfoReturnable<Slot> cir)` | `@Inject` 于 `getHoveredSlot` 的 `RETURN`，freeCursor 生效时用固定槽位覆盖原版基于鼠标的悬停 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias](../../alias/builtinAlias/FreeCursorAlias.java/README.md) | 其 `freeCursor` 标志门控此行为的别名 |
| [MouseMixin](../MouseMixin.java/README.md) | 为 freeCursor 抑制 OS 光标抓取和相机转向的鼠标 mixin |
