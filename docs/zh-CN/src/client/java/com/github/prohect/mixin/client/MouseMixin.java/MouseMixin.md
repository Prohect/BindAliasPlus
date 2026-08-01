# MouseMixin（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Mixin(net.minecraft.client.MouseHandler.class)
public class com.github.prohect.mixin.client.MouseMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.MouseHandler`，服务于两个独立目的：

**1. freeCursor 支持（3 个注入）：** 当 `FreeCursorAlias.freeCursor` 生效时：
- `skipOsCursorGrab` 取消 `grabMouse()`/`releaseMouse()` 内部的 OS 级 `grabOrReleaseMouse` GLFW 调用，保持宿主光标自由，同时游戏的逻辑抓取状态（门控挖掘和相机转向）正常进行。
- `skipCameraTurn` 取消 `turnPlayer()`，使物理鼠标增量不转动相机 —— 视角控制由 `yaw`/`pitch` 别名负责。
- `overrideIsMouseGrabbed` 强制 `isMouseGrabbed()` 返回 `true`，确保界面关闭后逻辑抓取为 false 时 `continueAttack`（按住挖掘）不被门控关闭。

**2. 鼠标按键路由：** `onMouseButton` 注入于 `onButton` 的 `HEAD`，将鼠标按键按下/松开事件路由到 `KEY_QUEUE`，按窗口句柄、锁定状态和绑定注册过滤 —— 与 `KeyBoardMixin.onKey` 类似，但针对鼠标按键。此外还由 `Alias.isUnderTextInputScreen()` 门控。

**3. 光标锁定后重新应用：** `lockCursor` 注入于 `grabMouse` 的 `RETURN`，对所有 `BuiltinAliasWithBooleanArgs` 实例调用 `reapplyToGameKeyMapping()`，在界面切换后将按住的按键别名与游戏的按键映射状态重新同步。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | freeCursor 生效时取消 OS 光标抓取 |
| [skipCameraTurn](skipCameraTurn.md) | 抑制鼠标增量引起的相机转向 |
| [overrideIsMouseGrabbed](overrideIsMouseGrabbed.md) | 强制 `isMouseGrabbed` 为 `true` 以保证挖掘连续性 |
| [onMouseButton](onMouseButton.md) | 将鼠标按键事件路由到 `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | 光标抓取后重新应用按住的别名 |
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | `freeCursor` 标志的来源 |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | 键盘事件的类似 mixin |
