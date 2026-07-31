# ForwardAlias (src/client/java/com/github/prohect/alias/builtinAlias/ForwardAlias.java)

Builtin alias that simulates the forward movement key (W key). Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ForwardAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.ForwardAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinForward"`. Usage: `+forward` to press, `-forward` to release. Press events are suppressed when a text-input screen is open; movement works on non-text GUI screens (inventory, etc.). Movement is injected via `KeyboardInputMixin`.

The implementation manipulates `Minecraft.options.keyUp`:

- `setDown(flag)` — holds or releases the key
- `clickCount++` — on press, increments the click counter

## See Also

| Item | Description |
|------|-------------|
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement equivalent |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | Left strafe equivalent |
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe equivalent |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Reads keyUp state for movement |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
