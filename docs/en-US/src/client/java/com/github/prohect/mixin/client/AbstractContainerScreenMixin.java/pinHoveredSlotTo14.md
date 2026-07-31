# pinHoveredSlotTo14 method (src/client/java/com/github/prohect/mixin/client/AbstractContainerScreenMixin.java)

## Syntax

```java
@Inject(method = "getHoveredSlot", at = @At("RETURN"), cancellable = true)
private void pinHoveredSlotTo14(double x, double y, CallbackInfoReturnable<Slot> cir)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `x` | `double` | Mouse x coordinate (unused) |
| `y` | `double` | Mouse y coordinate (unused) |
| `cir` | `CallbackInfoReturnable<Slot>` | Return override hook; set to the forced slot when freeCursor is active |

## Remarks

Injected at `RETURN` into vanilla `AbstractContainerScreen#getHoveredSlot(double, double)`. When `FreeCursorAlias.freeCursor` is `true`, searches the open container's slots for the one whose `containerSlot` equals `13` (player inventory slot 14, 1-indexed) and whose `container` is a `net.minecraft.world.entity.player.Inventory`, then sets it as the return value via `cir.setReturnValue(Slot)`. This overrides the vanilla mouse-based hover computation so that the hovered slot is always the agent's pinned slot while freeCursor is active. When freeCursor is `false`, returns early with no effect.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The boolean flag gating this injection |
| [AbstractContainerScreenMixin](AbstractContainerScreenMixin.md) | The enclosing mixin class |
