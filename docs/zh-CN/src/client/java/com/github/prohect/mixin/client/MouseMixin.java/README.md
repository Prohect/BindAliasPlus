# MouseMixin

针对 `net.minecraft.client.MouseHandler` 的 mixin。提供 freeCursor 支持（OS 抓取抑制、相机转向取消、isMouseGrabbed 覆盖），将鼠标按键事件路由到 `KEY_QUEUE`，并在光标抓取后重新应用按住的别名。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | `void skipOsCursorGrab(CallbackInfo ci)` | `@Inject` 于 `grabMouse`/`releaseMouse` 中 `grabOrReleaseMouse` 的 `INVOKE` —— freeCursor 生效时取消 OS 光标抓取 |
| [skipCameraTurn](skipCameraTurn.md) | `void skipCameraTurn(CallbackInfo ci)` | `@Inject` 于 `turnPlayer` 的 `HEAD` —— freeCursor 生效时抑制鼠标增量引起的相机转向 |
| [overrideIsMouseGrabbed](overrideIsMouseGrabbed.md) | `void overrideIsMouseGrabbed(CallbackInfoReturnable<Boolean> cir)` | `@Inject` 于 `isMouseGrabbed` 的 `RETURN` —— freeCursor 生效时强制返回 `true` |
| [onMouseButton](onMouseButton.md) | `void onMouseButton(long window, MouseButtonInfo button, int action, CallbackInfo ci)` | `@Inject` 于 `onButton` 的 `HEAD` —— 将鼠标按键事件路由到 `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | `void lockCursor(CallbackInfo ci)` | `@Inject` 于 `grabMouse` 的 `RETURN` —— 光标抓取后重新应用按住的别名 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | `freeCursor` 标志的来源 |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | 键盘事件的类似 mixin |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/README.md) | 提供 `LOCKED_PHYSICAL_KEYS` 集合 |
