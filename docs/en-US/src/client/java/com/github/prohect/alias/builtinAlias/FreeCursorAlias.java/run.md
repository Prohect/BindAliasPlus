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
   - Calls `MinecraftClient.getInstance().mouse.unlockCursor()` to drop the logical grab. Because `freeCursor` is still true at this point, `MouseMixin` skips the OS-level `unlockCursor()` call — avoiding an unwanted physical cursor jump. Once `cursorLocked` is false, the next real `lockCursor()` will properly re-apply the OS-level grab. Guarded so a stray `-freeCursor` when not grabbed is a no-op.
3. Sets `freeCursor = flag` — the static flag read by `MouseMixin`.

## See Also

| Item | Description |
|------|-------------|
| [freeCursor](freeCursor.md) | The static flag toggled by this method |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | Reads `freeCursor` to skip OS-level grab calls |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
