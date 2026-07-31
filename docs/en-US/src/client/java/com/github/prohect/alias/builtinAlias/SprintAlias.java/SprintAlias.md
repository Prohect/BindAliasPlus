# SprintAlias (src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java)

Switch alias (`+sprint` / `-sprint`) that simulates holding/releasing the sprint key (Ctrl). Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SprintAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SprintAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinSprint` (internal, exposed via `+sprint` / `-sprint`).

**Behavior:**
- `+sprint` (flag=1): Presses the vanilla `sprintKey` keybinding (Ctrl). When combined with `+forward`, the player sprints.
- `-sprint` (flag=0): Releases the `sprintKey` keybinding.

**Vanilla sprint mechanics:** Sprinting requires the sprint key to be held AND the forward key to be held simultaneously. Sprinting is cancelled if the player stops moving forward, collides with a block, or runs out of food (hunger <= 6).

**Screen suppression:** The press event (`+sprint`) is cancelled when `Alias.isUnderTextInputScreen()` returns true. The release event is never suppressed. On non-text screens, the alias continues to work.

**Reapply behavior:** Inherits from `BuiltinAliasWithBooleanArgs` — after a screen transition, if `flag` is true, `reapplyToGameKeyMapping()` re-applies the key. Listed in `ReapplyAlias.SUPPORTED_ACTIONS` as `"sprint"`.

**Movement injection:** Sets the keybinding state directly via `key.setPressed(flag)` which is intercepted by `KeyboardInputMixin` for window-focus-independent sprinting.

## See Also

| Item | Description |
|------|-------------|
| [SneakAlias](../SneakAlias.java/SneakAlias.md) | Sneak key (another modifier movement) |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement (required to actually sprint) |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Reapply held keys after screen transitions |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
