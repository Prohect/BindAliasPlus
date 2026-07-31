# freeCursor field (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

Static boolean flag that controls whether the OS-level cursor grab is suppressed. Read by `MouseMixin`.

## Syntax

```java
public static boolean freeCursor
```

## Remarks

When `true`, `MouseMixin` intercepts `Mouse.lockCursor()` and `Mouse.unlockCursor()` to skip only the OS-level GLFW cursor grab/release calls. The logical `cursorLocked` flag still transitions normally, so game logic that depends on mouse state (hold-to-mine, camera rotation) continues to work.

**Lifecycle:** Set by `FreeCursorAlias.run()` when `+freeCursor` or `-freeCursor` is executed. Default value is `false` (normal grabbed-cursor behavior).

**Readers:**
- `MouseMixin` — checks this flag to decide whether to skip OS-level grab calls
- `FreeCursorAlias.run()` — reads the current value to guard the `-freeCursor` transition

**Thread safety:** Only accessed from the game thread (via alias execution and mixin injection points); no synchronization needed.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias.run()](run.md) | Sets this flag |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | Reads this flag |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
