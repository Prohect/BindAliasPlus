# run method (src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java)

Handles escape actions: close current screen, and optionally open the pause menu.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.EscAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"0"` for close-only, `"1"` for toggle (close screen or open pause menu) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag` (0 or 1).
2. If any screen is currently open (`Alias.isUnderAnyScreen()`), calls `getCurrentScreen().close()` to close it, then returns. This path applies regardless of the flag value — closing always takes priority.
3. If no screen is open and `flag == 1` and `mc.player != null` (player is in a world):
   - Opens the pause menu via `mc.pauseGame(false)`. The `false` argument means the pause screen is not forced.
4. If `flag == 0` and no screen is open: no-op (close-only mode has nothing to close).

The `close-only` mode (`esc\0`) is used when a script wants to ensure no screen is open without risking accidentally opening the pause menu. The `toggle` mode (`esc\1`) matches vanilla Esc key behavior.

## See Also

| Item | Description |
|------|-------------|
| [Alias.isUnderAnyScreen()](../../Alias.java/isUnderAnyScreen.md) | Screen-detection helper |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
