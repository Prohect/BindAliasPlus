# HandledScreenMixin

Mixin targeting `net.minecraft.client.gui.screen.ingame.HandledScreen`. When `freeCursor` is active, forces `getSlotAt` to return the player inventory slot at index 13 (agent slot 14) so the hovered slot is pinned regardless of the OS cursor position.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `FORCED_HOVER_INDEX` | `int` (static, private, final, `13`) | 0-based player inventory index that maps to container slot 14 — the pinned hover target |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [pinFocusedSlotTo14](pinFocusedSlotTo14.md) | `void pinFocusedSlotTo14(double mouseX, double mouseY, CallbackInfoReturnable<Slot> cir)` | `@Inject` at `RETURN` of `getSlotAt` — replaces the return value with slot at `FORCED_HOVER_INDEX` when freeCursor is active |

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | Source of the `freeCursor` flag |
| [MouseMixin](../MouseMixin.java/README.md) | Suppresses OS cursor grab and camera turning during freeCursor |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
