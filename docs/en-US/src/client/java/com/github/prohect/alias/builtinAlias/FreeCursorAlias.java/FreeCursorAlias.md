# FreeCursorAlias (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

Builtin alias that toggles a special "free cursor" mode where the OS cursor remains free while the game logically behaves as if the mouse were grabbed. Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.FreeCursorAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.FreeCursorAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinFreeCursor"`. Usage: `+freeCursor` to enable, `-freeCursor` to disable.

When active, the static `freeCursor` flag is read by `MouseMixin`, which skips only the **OS-level** cursor grab call inside `lockCursor()` / `unlockCursor()`. The logical `cursorLocked` flag still turns on normally, so hold-to-mine (`continueAttack`) and other mouse-dependent game logic continue to work while the host cursor stays usable outside the game window. This is primarily a developer/testing convenience.

**Release-on-disable behavior:** When transitioning from enabled to disabled (`-freeCursor` while `freeCursor` was true), the code calls `Mouse.unlockCursor()` to drop the logical grab while `freeCursor` is still true. This causes `MouseMixin` to also skip the OS-level `unlockCursor` call — preventing an unwanted physical cursor jump. Once `cursorLocked` is false, the next real `lockCursor()` re-applies the OS-level grab normally. A stray `-freeCursor` when not grabbed is a no-op (guarded by the previous state check).

## Fields

| Name | Type | Description |
|------|------|-------------|
| [freeCursor](freeCursor.md) | `public static boolean` | Flag read by `MouseMixin`; when true, skips OS-level cursor grab |

## See Also

| Item | Description |
|------|-------------|
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | Reads `freeCursor` to skip OS-level grab calls |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
