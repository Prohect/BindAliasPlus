# freeCursor field (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

Static boolean flag read by `MouseMixin.cancelGrabMouse()` to decide whether to cancel the mouse-grab call.

## Syntax

```java
public static boolean freeCursor
```

## Remarks

Set to `true` by `+freeCursor`, `false` by `-freeCursor`. Checked at the HEAD of `grabMouse()` in the mixin — when true, `CallbackInfo.cancel()` is called, preventing the cursor from being locked to the game window.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias](FreeCursorAlias.md) | The alias that sets this flag |
| [run](run.md) | Method that sets this flag |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAliasPlus/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
