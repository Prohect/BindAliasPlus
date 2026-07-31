# MouseMixin

Mixin targeting `net.minecraft.client.MouseHandler`. Provides freeCursor support (OS grab suppression, camera-turn cancellation, isMouseGrabbed override), routes mouse button events to `KEY_QUEUE`, and reapplies held aliases after cursor grab.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | `void skipOsCursorGrab(CallbackInfo ci)` | `@Inject` at `INVOKE` of `grabOrReleaseMouse` in `grabMouse`/`releaseMouse` — cancels OS cursor grab when freeCursor is active |
| [skipCameraTurn](skipCameraTurn.md) | `void skipCameraTurn(CallbackInfo ci)` | `@Inject` at `HEAD` of `turnPlayer` — suppresses camera turning from mouse deltas when freeCursor is active |
| [overrideIsMouseGrabbed](overrideIsMouseGrabbed.md) | `void overrideIsMouseGrabbed(CallbackInfoReturnable<Boolean> cir)` | `@Inject` at `RETURN` of `isMouseGrabbed` — forces `true` when freeCursor is active |
| [onMouseButton](onMouseButton.md) | `void onMouseButton(long window, MouseButtonInfo button, int action, CallbackInfo ci)` | `@Inject` at `HEAD` of `onButton` — routes mouse button events to `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | `void lockCursor(CallbackInfo ci)` | `@Inject` at `RETURN` of `grabMouse` — reapplies held aliases after cursor grab |

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | Source of the `freeCursor` flag |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | Analogous mixin for keyboard events |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/README.md) | Provides the `LOCKED_PHYSICAL_KEYS` set |
