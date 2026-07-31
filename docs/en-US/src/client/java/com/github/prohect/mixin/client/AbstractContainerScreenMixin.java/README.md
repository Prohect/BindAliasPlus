# AbstractContainerScreenMixin

Mixin targeting `net.minecraft.client.gui.screens.inventory.AbstractContainerScreen`. Pins the hovered slot to slot 14 when freeCursor is active, so agent drop/swap operations always target a deterministic slot regardless of the host cursor position.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `FORCED_HOVER_CONTAINER_SLOT` | `int` (static, private, `13`) | The 0-based containerSlot index to force-hover (player inventory slot 14) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [pinHoveredSlotTo14](pinHoveredSlotTo14.md) | `void pinHoveredSlotTo14(double x, double y, CallbackInfoReturnable<Slot> cir)` | `@Inject` at `RETURN` of `getHoveredSlot` to override vanilla's mouse-based hover with a fixed slot when freeCursor is active |

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias](../../alias/builtinAlias/FreeCursorAlias.java/README.md) | The alias whose `freeCursor` flag gates this behavior |
| [MouseMixin](../MouseMixin.java/README.md) | The mouse mixin that suppresses OS cursor grab and camera turning for freeCursor |
