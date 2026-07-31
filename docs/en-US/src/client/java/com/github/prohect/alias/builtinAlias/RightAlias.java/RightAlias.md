# RightAlias (src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java)

Switch alias (`+right` / `-right`) that simulates holding/releasing the strafe-right key (D). Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.RightAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.RightAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinRight` (internal, exposed via `+right` / `-right`).

**Behavior:**
- `+right` (flag=1): Presses the vanilla `rightKey` keybinding (D), making the player strafe right.
- `-right` (flag=0): Releases the `rightKey` keybinding.

This alias mirrors `LeftAlias` exactly, just targeting the opposite lateral movement direction.

**Screen suppression:** The press event (`+right`) is cancelled when `Alias.isUnderTextInputScreen()` returns true. The release event is never suppressed. On non-text screens, the alias continues to work (unlike `AttackAlias` and `UseAlias` which are suppressed on all screens).

**Reapply behavior:** Inherits from `BuiltinAliasWithBooleanArgs` — after a screen transition, if `flag` is true, `reapplyToGameKeyMapping()` re-applies the key. Listed in `ReapplyAlias.SUPPORTED_ACTIONS` as `"right"`. Movement injection is also handled by `KeyboardInputMixin` for the `+right` key.

**Movement injection:** Unlike vanilla, this alias fires `key.setPressed(flag)` directly on the `KeyBinding`, which is intercepted by `KeyboardInputMixin.tick()` to inject movement impulses even when the game window doesn't have focus. This is distinct from the vanilla key-press queue, which requires window focus.

## See Also

| Item | Description |
|------|-------------|
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | Opposite horizontal movement (strafe left) |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement |
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Reapply held keys after screen transitions |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
