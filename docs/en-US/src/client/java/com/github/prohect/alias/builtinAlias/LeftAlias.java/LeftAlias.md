# LeftAlias (src/client/java/com/github/prohect/alias/builtinAlias/LeftAlias.java)

Builtin alias that simulates the left strafe movement key (A key). Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LeftAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.LeftAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinLeft"`. Usage: `+left` to press, `-left` to release. Press events are suppressed on text-input screens; works on non-text GUI screens. Movement is injected via `KeyboardInputMixin`.

The implementation manipulates `MinecraftClient.getInstance().options.leftKey`:

- `setPressed(flag)` — holds or releases the key
- `timesPressed++` — on press, increments the click counter

## See Also

| Item | Description |
|------|-------------|
| [RightAlias](../RightAlias.java/RightAlias.md) | Right strafe equivalent |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Forward movement |
| [BackAlias](../BackAlias.java/BackAlias.md) | Backward movement |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Reads leftKey state for movement |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
