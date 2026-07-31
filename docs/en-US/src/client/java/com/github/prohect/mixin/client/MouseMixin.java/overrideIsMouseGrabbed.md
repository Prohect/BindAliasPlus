# overrideIsMouseGrabbed method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Inject(method = "isMouseGrabbed", at = @At("RETURN"), cancellable = true)
private void overrideIsMouseGrabbed(CallbackInfoReturnable<Boolean> cir)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `cir` | `CallbackInfoReturnable<Boolean>` | Return override; set to `true` when freeCursor is active |

## Remarks

Injected at `RETURN` of `MouseHandler#isMouseGrabbed()`. When `FreeCursorAlias.freeCursor` is `true`, overrides the return value to `true` via `cir.setReturnValue(true)`. Vanilla's `Minecraft#handleKeybinds` gates `continueAttack` (hold-to-mine) on `isMouseGrabbed()`. After a screen opens and calls `releaseMouse()`, the logical grab may be `false`, which would interrupt mining. Because the OS-level grab was suppressed (see `skipOsCursorGrab`) but the logical grab was not, this override ensures mining continues as if the cursor were still grabbed.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The flag gating this injection |
| [skipOsCursorGrab](skipOsCursorGrab.md) | Suppresses the OS-level cursor grab |
