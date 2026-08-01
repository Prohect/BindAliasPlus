# MouseMixin（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Mixin(net.minecraft.client.Mouse.class)
public class com.github.prohect.mixin.client.MouseMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.Mouse`，服务于三个独立目的：

**1. freeCursor 支持（3 个注入）：** 当 `FreeCursorAlias.freeCursor` 激活时：
- `skipOsCursorGrab` 取消 `lockCursor()`/`unlockCursor()` 中 OS 级别的 `setCursorParameters` GLFW 调用，使宿主光标保持自由，同时游戏的逻辑锁定状态（控制挖掘和相机转动）正常进行。
- `skipCameraTurn` 取消 `updateMouse()`，使物理鼠标增量不会转动相机——视角控制由 `yaw`/`pitch` 别名接管。
- `overrideIsCursorLocked` 强制 `isCursorLocked()` 返回 `true`，确保界面关闭后逻辑锁定为 false 时 `handleBlockBreaking`（按住挖掘）不会被关掉。

**2. 鼠标按钮路由：** `onMouseButton` 的 `HEAD` 处 `onMouseButton` 将鼠标按钮按下/松开事件路由到 `KEY_QUEUE`，按窗口句柄、锁定状态和绑定注册过滤——与 `KeyBoardMixin.onKey` 类似，但针对鼠标按钮。另外受 `Alias.isUnderTextInputScreen()` 把关。

**3. 光标锁定后重新应用：** `lockCursor` 的 `RETURN` 处 `lockCursor` 对所有 `BuiltinAliasWithBooleanArgs` 实例调用 `reapplyToGameKeyMapping()`，在界面切换后重新同步按住的按键别名与游戏的按键映射状态。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | freeCursor 激活时取消 OS 光标锁定 |
| [skipCameraTurn](skipCameraTurn.md) | 抑制鼠标增量引起的相机转动 |
| [overrideIsCursorLocked](overrideIsCursorLocked.md) | 强制 `isCursorLocked` 为 `true` 以保持挖掘连续性 |
| [onMouseButton](onMouseButton.md) | 将鼠标按钮事件路由到 `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | 光标锁定后重新应用按住的别名 |
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | `freeCursor` 标志的来源 |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | 键盘事件的类似 mixin |
