# skipOsCursorGrab 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/util/InputUtil;setCursorParameters(JIDD)V"),
        method = {"lockCursor", "unlockCursor"}, cancellable = true)
private void skipOsCursorGrab(CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ci` | `CallbackInfo` | freeCursor 激活时取消以跳过 OS 级别锁定 |

## 备注

注入到 `lockCursor()` 和 `unlockCursor()` 中 `InputUtil.setCursorParameters` 的 `INVOKE` 处。当 `FreeCursorAlias.freeCursor` 为 `true` 时，用 `ci.cancel()` 取消回调，阻止实际的 GLFW `glfwSetCursorPos` + `glfwSetInputMode(GLFW_CURSOR, ...)` 调用。原版方法仍会运行逻辑锁定状态变更（影响 `handleBlockBreaking` 和相机转动）；只有 OS 级别光标锁定被抑制，因此宿主光标保持自由，即使游戏表现得像是已锁定。（Yarn：`setCursorParameters()`；Mojang：`grabOrReleaseMouse()`）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 把关此注入的标志 |
| [skipCameraTurn](skipCameraTurn.md) | 抑制由鼠标增量驱动的相机转动 |
| [overrideIsCursorLocked](overrideIsCursorLocked.md) | 强制 `isCursorLocked` 为 `true` |
