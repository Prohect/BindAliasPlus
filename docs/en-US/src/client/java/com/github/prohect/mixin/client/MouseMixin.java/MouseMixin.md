# MouseMixin (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Mixin(net.minecraft.client.MouseHandler.class)
public class com.github.prohect.mixin.client.MouseMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.MouseHandler` and serves two independent purposes:

**1. freeCursor support (3 injections):** When `FreeCursorAlias.freeCursor` is active:
- `skipOsCursorGrab` cancels the OS-level `grabOrReleaseMouse` GLFW call inside `grabMouse()`/`releaseMouse()`, keeping the host cursor free while the game's logical grab state (which gates mining and camera turning) proceeds normally.
- `skipCameraTurn` cancels `turnPlayer()` so that physical mouse deltas don't turn the camera — view control stays with `yaw`/`pitch` aliases.
- `overrideIsMouseGrabbed` forces `isMouseGrabbed()` to return `true`, ensuring `continueAttack` (hold-to-mine) is not gated off when the logical grab is false after a screen close.

**2. Mouse button routing:** `onMouseButton` at `HEAD` of `onButton` routes mouse button press/release events to `KEY_QUEUE`, filtered by window handle, lock state, and binding registration — analogous to `KeyBoardMixin.onKey` but for mouse buttons. Additionally gated by `Alias.isUnderTextInputScreen()`.

**3. Reapply on cursor lock:** `lockCursor` at `RETURN` of `grabMouse` calls `reapplyToGameKeyMapping()` on all `BuiltinAliasWithBooleanArgs` instances, re-synchronizing held key aliases with the game's key-mapping state after a screen transition.

## See Also

| Item | Description |
|------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | Cancels OS cursor grab when freeCursor is active |
| [skipCameraTurn](skipCameraTurn.md) | Suppresses camera turning from mouse deltas |
| [overrideIsMouseGrabbed](overrideIsMouseGrabbed.md) | Forces `isMouseGrabbed` to `true` for mining continuity |
| [onMouseButton](onMouseButton.md) | Routes mouse button events to `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | Reapplies held aliases after cursor grab |
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | Source of the `freeCursor` flag |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | Analogous mixin for keyboard events |
