# EscAlias (src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java)

Screen-control alias for closing screens and opening the pause menu.

## Syntax

```java
public class EscAlias extends BuiltinAliasWithIntegerArgs<EscAlias>
```

## Static Initializer

_None._

## Remarks

Two modes controlled by the integer argument:

- **`esc\0` (close only):** Closes the current screen via `onClose()`. No-op if no screen is open. User shortcut: `-esc`.
- **`esc\1` (toggle):** If a screen is open, closes it. If no screen is open and the player is in a world, opens the pause menu via `pauseGame(false)`. User shortcut: `+esc`.

This is designed for agent control — an agent can use `esc\0` to escape any unexpected screen back to the game world, or `esc\1` to toggle pause for timing/safety.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Entry point |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
