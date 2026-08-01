# skipOsCursorGrab 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At(value = "INVOKE",
        target = "Lcom/mojang/blaze3d/platform/InputConstants;grabOrReleaseMouse(Lcom/mojang/blaze3d/platform/Window;IDD)V"),
        method = {"grabMouse", "releaseMouse"}, cancellable = true)
private void skipOsCursorGrab(CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ci` | `CallbackInfo` | freeCursor 生效时被取消以跳过 OS 级抓取 |

## 备注

注入于 `grabMouse()` 和 `releaseMouse()` 内部 `InputConstants.grabOrReleaseMouse` 的 `INVOKE`。当 `FreeCursorAlias.freeCursor` 为 `true` 时，用 `ci.cancel()` 取消回调，阻止实际的 GLFW `glfwSetCursorPos` + `glfwSetInputMode(GLFW_CURSOR, ...)` 调用。原版方法仍会运行逻辑抓取状态变更（影响 `continueAttack` 和相机转向）；只有 OS 级光标锁定被抑制，因此宿主光标保持自由，尽管游戏表现得像已抓取一样。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 门控此注入的标志 |
| [skipCameraTurn](skipCameraTurn.md) | 抑制鼠标增量驱动的相机转向 |
| [overrideIsMouseGrabbed](overrideIsMouseGrabbed.md) | 强制 `isMouseGrabbed` 为 `true` |
