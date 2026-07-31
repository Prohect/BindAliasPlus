# run method (src/client/java/com/github/prohect/alias/builtinAlias/ForwardAlias.java)

Handles `+forward` (press) and `-forward` (release) by manipulating the vanilla forward key binding.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ForwardAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+forward`), `"0"` for release (`-forward`) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only, text-input only):** If `flag` is true and a text-input screen is open, returns immediately. Forward movement works on non-text GUI screens. Release events always process.
3. Retrieves `Minecraft.options.keyUp` and calls `setDown(flag)` plus `clickCount++` on press.

Movement is applied each tick by `KeyboardInputMixin`, which reads `keyUp.isDown()`.

## See Also

| Item | Description |
|------|-------------|
| [BackAlias.run()](../BackAlias.java/run.md) | Same pattern for backward |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Reads the key state for movement |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
