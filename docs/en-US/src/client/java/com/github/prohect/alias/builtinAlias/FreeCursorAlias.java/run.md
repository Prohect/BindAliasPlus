# run method (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

Handles `+freeCursor` (enable) and `-freeCursor` (disable) with careful mouse grab state management.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.FreeCursorAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for enable (`+freeCursor`), `"0"` for disable (`-freeCursor`) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Disable transition (`!flag && freeCursor`):** When switching from enabled to disabled while `freeCursor` is still true:
   - Calls `Minecraft.mouseHandler.releaseMouse()` to drop the logical grab. Because `freeCursor` is still true at this point, `MouseMixin` skips the OS-level `releaseMouse()` call — avoiding an unwanted physical cursor jump. Once `mouseGrabbed` is false, the next real `grabMouse()` will properly re-apply the OS-level grab. Guarded so a stray `-freeCursor` when not grabbed is a no-op.
3. Sets `freeCursor = flag` — the static flag read by `MouseMixin`.

## See Also

| Item | Description |
|------|-------------|
| [freeCursor](freeCursor.md) | The static flag toggled by this method |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | Reads `freeCursor` to skip OS-level grab calls |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
