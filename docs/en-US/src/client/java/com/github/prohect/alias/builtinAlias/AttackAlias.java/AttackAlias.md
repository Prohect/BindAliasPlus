# AttackAlias (src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java)

Builtin alias that simulates the attack (left-click) key binding. Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.AttackAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.AttackAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinAttack"`. Usage: `+attack` to press, `-attack` to release. Press events (`flag == true`) are suppressed when a text-input screen (chat, sign, book, command block) is open — the attack key is never injected into typed text. However, unlike movement keys, the attack key is also blocked on **all** screens (via `BuiltinAliasWithBooleanArgs`'s screen suppression inherited from `UserAlias.run()` and the `addToScreenBlackList` registration) for safety: attacking in a GUI could have unintended side effects.

The implementation manipulates `Minecraft.options.keyAttack`:

- `setDown(flag)` — holds or releases the key
- `clickCount++` — on press, increments the click counter so that vanilla click detection (tap vs. hold) fires

No `reapplyToGameKeyMapping()` override — the default inherited from `BuiltinAliasWithBooleanArgs` re-runs with `"1"` after screen transitions when `flag` is true.

## See Also

| Item | Description |
|------|-------------|
| [UseAlias](../UseAlias.java/UseAlias.md) | Analogous for right-click |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Movement key (only text-input suppressed) |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
