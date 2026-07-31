# BackAlias (src/client/java/com/github/prohect/alias/builtinAlias/BackAlias.java)

Builtin alias that simulates the backward movement key (S key). Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.BackAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.BackAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinBack"`. Usage: `+back` to press, `-back` to release. Press events are suppressed when a text-input screen is open, but movement keys **do** work on non-text screens (inventory, crafting table, etc.), unlike Attack/Use which are blocked on all screens. Movement is injected via `KeyboardInputMixin` which reads the down-state of the vanilla KeyBinding.

The implementation manipulates `MinecraftClient.getInstance().options.backKey`:

- `setPressed(flag)` — holds or releases the key
- `timesPressed++` — on press, increments the click counter

## See Also

| Item | Description |
|------|-------------|
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement equivalent |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | Left strafe equivalent |
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe equivalent |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Reads backKey state for movement |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
