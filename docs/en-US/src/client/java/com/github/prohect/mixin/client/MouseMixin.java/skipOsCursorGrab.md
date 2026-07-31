# skipOsCursorGrab method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Inject(at = @At(value = "INVOKE",
        target = "Lcom/mojang/blaze3d/platform/InputConstants;grabOrReleaseMouse(Lcom/mojang/blaze3d/platform/Window;IDD)V"),
        method = {"grabMouse", "releaseMouse"}, cancellable = true)
private void skipOsCursorGrab(CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `ci` | `CallbackInfo` | Cancelled when freeCursor is active to skip the OS-level grab |

## Remarks

Injected at the `INVOKE` of `InputConstants.grabOrReleaseMouse` inside both `grabMouse()` and `releaseMouse()`. When `FreeCursorAlias.freeCursor` is `true`, cancels the callback with `ci.cancel()`, preventing the actual GLFW `glfwSetCursorPos` + `glfwSetInputMode(GLFW_CURSOR, ...)` call. The vanilla method still runs the logical-grab state changes (affecting `continueAttack` and camera turning); only the OS-level cursor lock is suppressed, so the host cursor remains free even though the game behaves as if it were grabbed.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The flag gating this injection |
| [skipCameraTurn](skipCameraTurn.md) | Suppresses camera turning driven by mouse deltas |
| [overrideIsMouseGrabbed](overrideIsMouseGrabbed.md) | Forces `isMouseGrabbed` to `true` |
