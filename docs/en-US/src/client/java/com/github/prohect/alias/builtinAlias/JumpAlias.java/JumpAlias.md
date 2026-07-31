# JumpAlias (src/client/java/com/github/prohect/alias/builtinAlias/JumpAlias.java)

Builtin alias that simulates the jump key binding (spacebar). Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.JumpAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.JumpAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinJump"`. Usage: `+jump` to press, `-jump` to release. Press events are suppressed on text-input screens; jump works on non-text screens. Holding `+jump` keeps the player jumping on ground and swimming upward in water, matching vanilla behavior.

The implementation manipulates `Minecraft.options.keyJump`:

- `setDown(flag)` — holds or releases the key
- `clickCount++` — on press, increments the click counter

## See Also

| Item | Description |
|------|-------------|
| [SneakAlias](../SneakAlias.java/SneakAlias.md) | Sneak key equivalent |
| [SprintAlias](../SprintAlias.java/SprintAlias.md) | Sprint key equivalent |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement key |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
