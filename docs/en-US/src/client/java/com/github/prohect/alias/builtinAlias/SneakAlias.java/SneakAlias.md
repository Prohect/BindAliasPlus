# SneakAlias (src/client/java/com/github/prohect/alias/builtinAlias/SneakAlias.java)

Switch alias (`+sneak` / `-sneak`) that simulates holding/releasing the sneak key (Shift). Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SneakAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SneakAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinSneak` (internal, exposed via `+sneak` / `-sneak`).

**Behavior:**
- `+sneak` (flag=1): Presses the vanilla `sneakKey` keybinding (Shift), making the player sneak/crouch. Sneaking prevents falling off edges and reduces player height.
- `-sneak` (flag=0): Releases the `sneakKey` keybinding.

**Screen suppression:** The press event (`+sneak`) is cancelled when `Alias.isUnderTextInputScreen()` returns true. The release event is never suppressed. On non-text screens (inventory, container, etc.), the alias continues to work.

**Reapply behavior:** Inherits from `BuiltinAliasWithBooleanArgs` — after a screen transition, if `flag` is true, `reapplyToGameKeyMapping()` re-applies the key. Listed in `ReapplyAlias.SUPPORTED_ACTIONS` as `"sneak"`.

**Movement injection:** Like other movement aliases, this sets the keybinding state directly via `key.setPressed(flag)` which is intercepted by `KeyboardInputMixin` for window-focus-independent movement. The `timesPressed` is incremented on the press event so the game registers the initial press-action.

## See Also

| Item | Description |
|------|-------------|
| [SprintAlias](../SprintAlias.java/SprintAlias.md) | Sprint key (another modifier movement key) |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Reapply held keys after screen transitions |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
