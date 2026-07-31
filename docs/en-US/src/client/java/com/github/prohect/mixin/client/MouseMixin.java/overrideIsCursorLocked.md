# overrideIsCursorLocked method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Inject(method = "isCursorLocked", at = @At("RETURN"), cancellable = true)
private void overrideIsCursorLocked(CallbackInfoReturnable<Boolean> cir)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `cir` | `CallbackInfoReturnable<Boolean>` | Callback to override the return value |

## Remarks

Injected at `RETURN` of `Mouse#isCursorLocked()`. When `FreeCursorAlias.freeCursor` is `true`, forces the return value to `true` via `cir.setReturnValue(true)`.

**Purpose:** `isCursorLocked` gates hold-to-mine (`handleBlockBreaking`) in `MinecraftClient#handleInputEvents`. While freeCursor is active, the logical grab may be `false` — for example, after a screen opens and calls `unlockCursor()`, the vanilla cursor lock state is released. However, the agent still needs mining to continue as if the cursor were grabbed. By overriding `isCursorLocked` to always return `true` during freeCursor, the mining guard is bypassed and `+attack` continues to work.

This is one of three freeCursor-supporting injections in `MouseMixin`:
- `skipOsCursorGrab`: cancels the OS-level `glfwSetInputMode` call so the host cursor stays free.
- `skipCameraTurn`: cancels `updateMouse` to prevent mouse deltas from turning the camera.
- `overrideIsCursorLocked` (this method): ensures the logical grab state never gates mining.

When `freeCursor` is `false`, the injection returns without modification and vanilla `isCursorLocked` behavior is unchanged.

The 26.x (Mojang) equivalent was called `overrideIsMouseGrabbed` and targeted `Mouse#isMouseGrabbed()` — the rename reflects the Yarn mapping where the method is `isCursorLocked` on the `Mouse` class.

## See Also

| Item | Description |
|------|-------------|
| [MouseMixin](MouseMixin.md) | The enclosing mixin class |
| [skipOsCursorGrab](skipOsCursorGrab.md) | Suppresses OS-level cursor grab during freeCursor |
| [skipCameraTurn](skipCameraTurn.md) | Suppresses camera turning from mouse deltas during freeCursor |
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The flag gating this injection |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
