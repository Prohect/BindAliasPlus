# MouseMixin

针对 `net.minecraft.client.Mouse` 的 mixin。提供 freeCursor 支持（OS 锁定抑制、相机转动取消、isCursorLocked 覆盖）、将鼠标按钮事件路由到 `KEY_QUEUE`，并在光标锁定后重新应用按住的别名。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | `void skipOsCursorGrab(CallbackInfo ci)` | `lockCursor`/`unlockCursor` 中 `setCursorParameters` 的 `INVOKE` 处 `@Inject`——freeCursor 激活时取消 OS 光标锁定 |
| [skipCameraTurn](skipCameraTurn.md) | `void skipCameraTurn(CallbackInfo ci)` | `updateMouse` 的 `HEAD` 处 `@Inject`——freeCursor 激活时抑制鼠标增量引起的相机转动 |
| [overrideIsCursorLocked](overrideIsCursorLocked.md) | `void overrideIsCursorLocked(CallbackInfoReturnable<Boolean> cir)` | `isCursorLocked` 的 `RETURN` 处 `@Inject`——freeCursor 激活时强制为 `true` |
| [onMouseButton](onMouseButton.md) | `void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci)` | `onMouseButton` 的 `HEAD` 处 `@Inject`——将鼠标按钮事件路由到 `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | `void lockCursor(CallbackInfo ci)` | `lockCursor` 的 `RETURN` 处 `@Inject`——光标锁定后重新应用按住的别名 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | `freeCursor` 标志的来源 |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | 键盘事件的类似 mixin |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/README.md) | 提供 `LOCKED_PHYSICAL_KEYS` 集合 |
