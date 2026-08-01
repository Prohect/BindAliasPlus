# pinHoveredSlotTo14 方法（src/client/java/com/github/prohect/mixin/client/AbstractContainerScreenMixin.java）

## 语法

```java
@Inject(method = "getHoveredSlot", at = @At("RETURN"), cancellable = true)
private void pinHoveredSlotTo14(double x, double y, CallbackInfoReturnable<Slot> cir)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `x` | `double` | 鼠标 x 坐标（未使用） |
| `y` | `double` | 鼠标 y 坐标（未使用） |
| `cir` | `CallbackInfoReturnable<Slot>` | 返回值覆盖钩子；freeCursor 生效时设置为强制槽位 |

## 备注

注入于原版 `AbstractContainerScreen#getHoveredSlot(double, double)` 的 `RETURN`。当 `FreeCursorAlias.freeCursor` 为 `true` 时，在打开的容器的槽位中查找 `containerSlot` 等于 `13`（玩家物品栏槽位 14，1 基）且 `container` 是 `net.minecraft.world.entity.player.Inventory` 的槽位，然后通过 `cir.setReturnValue(Slot)` 将其设为返回值。这覆盖了原版基于鼠标的悬停计算，使 freeCursor 生效期间悬停槽位始终是代理的固定槽位。freeCursor 为 `false` 时提前返回，无效果。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 门控此注入的布尔标志 |
| [AbstractContainerScreenMixin](AbstractContainerScreenMixin.md) | 所属 mixin 类 |
