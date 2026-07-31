# pinFocusedSlotTo14 method (src/client/java/com/github/prohect/mixin/client/HandledScreenMixin.java)

## Syntax

```java
@Inject(method = "getSlotAt", at = @At("RETURN"), cancellable = true)
private void pinFocusedSlotTo14(double mouseX, double mouseY, CallbackInfoReturnable<Slot> cir)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `mouseX` | `double` | Mouse X position in screen coordinates (unused) |
| `mouseY` | `double` | Mouse Y position in screen coordinates (unused) |
| `cir` | `CallbackInfoReturnable<Slot>` | Callback to replace the return value |

## Remarks

Injected at `RETURN` of `HandledScreen#getSlotAt(double, double)`. When `FreeCursorAlias.freeCursor` is `false`, returns immediately (no-op), leaving vanilla hover behavior unchanged.

When `freeCursor` is `true`:
1. Casts `this` to `HandledScreen<?>` via `(HandledScreen<?>) (Object) this`.
2. Iterates all slots in `self.handler.slots` (the `ScreenHandler`).
3. Finds the slot whose `index` equals `FORCED_HOVER_INDEX` (13) **and** whose `inventory` is an instance of `PlayerInventory` — the extra check ensures the pinned slot is genuinely a player inventory slot, not a container slot that coincidentally has index 13.
4. Calls `cir.setReturnValue(slot)` to replace the vanilla return value.
5. If no matching slot is found, the vanilla return value is left unchanged (fallback to normal hover).

This injection is the sole mechanism for pinning the hovered slot during freeCursor. By overriding `getSlotAt` at its return point, all downstream consumers of the focused slot (drop, swap, tooltip rendering) see the pinned slot without needing individual injections. The return point is used rather than `HEAD` because the vanilla method does its own slot-finding logic first, which is harmless — the mixin simply replaces the result afterward.

## See Also

| Item | Description |
|------|-------------|
| [HandledScreenMixin](HandledScreenMixin.md) | The enclosing mixin class |
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The flag gating this injection |
| [MouseMixin.skipOsCursorGrab](../MouseMixin.java/skipOsCursorGrab.md) | Suppresses OS cursor grab during freeCursor |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
