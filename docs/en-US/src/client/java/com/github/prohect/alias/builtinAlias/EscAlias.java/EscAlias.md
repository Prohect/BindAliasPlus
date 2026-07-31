# EscAlias (src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java)

Builtin alias that closes the current screen or toggles the pause menu. Unlike the simple switch aliases, this extends `BuiltinAliasWithIntegerArgs` because it supports two modes: close-only (`\0`) and toggle (`\1`).

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.EscAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.EscAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinEsc"`. The integer argument distinguishes two behaviors:

- **`esc\0`** (`flag == 0`, close-only): Closes the current screen if one is open. If no screen is open, does nothing.
- **`esc\1`** (`flag == 1`, toggle): Closes the current screen if one is open. If no screen is open and the player is in a world (`mc.player != null`), opens the pause menu via `mc.pauseGame(false)`.

User-facing shortcuts map to these modes: `+esc` → toggle (flag=1), `-esc` → close-only (flag=0).

## See Also

| Item | Description |
|------|-------------|
| [closeScreen](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | Dedicated close-only alias |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Base class for integer-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
