# MouseMixin (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Mixin(net.minecraft.client.Mouse.class)
public class com.github.prohect.mixin.client.MouseMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.Mouse` and serves three independent purposes:

**1. freeCursor support (3 injections):** When `FreeCursorAlias.freeCursor` is active:
- `skipOsCursorGrab` cancels the OS-level `setCursorParameters` GLFW call inside `lockCursor()`/`unlockCursor()`, keeping the host cursor free while the game's logical grab state (which gates mining and camera turning) proceeds normally.
- `skipCameraTurn` cancels `updateMouse()` so that physical mouse deltas don't turn the camera — view control stays with `yaw`/`pitch` aliases.
- `overrideIsCursorLocked` forces `isCursorLocked()` to return `true`, ensuring `handleBlockBreaking` (hold-to-mine) is not gated off when the logical grab is false after a screen close.

**2. Mouse button routing:** `onMouseButton` at `HEAD` of `onMouseButton` routes mouse button press/release events to `KEY_QUEUE`, filtered by window handle, lock state, and binding registration — analogous to `KeyBoardMixin.onKey` but for mouse buttons. Additionally gated by `Alias.isUnderTextInputScreen()`.

**3. Reapply on cursor lock:** `lockCursor` at `RETURN` of `lockCursor` calls `reapplyToGameKeyMapping()` on all `BuiltinAliasWithBooleanArgs` instances, re-synchronizing held key aliases with the game's key-mapping state after a screen transition.

## See Also

| Item | Description |
|------|-------------|
| [skipOsCursorGrab](skipOsCursorGrab.md) | Cancels OS cursor grab when freeCursor is active |
| [skipCameraTurn](skipCameraTurn.md) | Suppresses camera turning from mouse deltas |
| [overrideIsCursorLocked](overrideIsCursorLocked.md) | Forces `isCursorLocked` to `true` for mining continuity |
| [onMouseButton](onMouseButton.md) | Routes mouse button events to `KEY_QUEUE` |
| [lockCursor](lockCursor.md) | Reapplies held aliases after cursor grab |
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | Source of the `freeCursor` flag |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | Analogous mixin for keyboard events |
