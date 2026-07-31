# skipCameraTurn method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Inject(at = @At("HEAD"), method = "updateMouse", cancellable = true)
private void skipCameraTurn(CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `ci` | `CallbackInfo` | Cancelled when freeCursor is active |

## Remarks

Injected at `HEAD` of `Mouse#updateMouse()`. When `FreeCursorAlias.freeCursor` is `true`, cancels the method entirely so physical mouse delta movements do not rotate the player's camera. This is necessary because freeCursor allows the logical mouse-grab to remain in effect (for mining continuity), which would otherwise re-enable camera turning when the mouse moves over the focused window. Camera control during freeCursor is exclusively via the `yaw`/`pitch`/`setYaw`/`setPitch` aliases. (Yarn: `updateMouse()`; Mojang: `turnPlayer()`)

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The flag gating this injection |
| [skipOsCursorGrab](skipOsCursorGrab.md) | Suppresses the OS-level cursor grab |
| [YawAlias](../../../alias/builtinAlias/YawAlias.java/README.md) | The `yaw` alias for camera rotation |
| [PitchAlias](../../../alias/builtinAlias/PitchAlias.java/README.md) | The `pitch` alias for camera rotation |
