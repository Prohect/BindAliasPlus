# run method (src/client/java/com/github/prohect/alias/builtinAlias/LeftAlias.java)

Handles `+left` (press) and `-left` (release) by manipulating the vanilla left strafe key binding.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.LeftAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+left`), `"0"` for release (`-left`) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only, text-input only):** If `flag` is true and a text-input screen is open, returns immediately. Left strafe works on non-text screens. Release events always process.
3. Retrieves `Minecraft.options.keyLeft` and calls `setDown(flag)` plus `clickCount++` on press.

Movement is applied each tick by `KeyboardInputMixin`, which reads `keyLeft.isDown()`.

## See Also

| Item | Description |
|------|-------------|
| [RightAlias.run()](../RightAlias.java/run.md) | Same pattern for right strafe |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Reads the key state for movement |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
