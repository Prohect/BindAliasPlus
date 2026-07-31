# run method (src/client/java/com/github/prohect/alias/builtinAlias/BackAlias.java)

Handles `+back` (press) and `-back` (release) by manipulating the vanilla backward key binding.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.BackAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+back`), `"0"` for release (`-back`), as parsed by `parseArgs(args)` |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only, text-input only):** If `flag` is true and a text-input screen is open, returns immediately. Unlike Attack/Use, movement keys are only blocked on text-input screens — they function normally on GUI screens like inventory and crafting tables. Release events are still processed to avoid stuck keys.
3. Retrieves `Minecraft.options.keyDown` and calls `setDown(flag)` plus `clickCount++` on press.

Movement is read each tick by `KeyboardInputMixin`, which checks `keyDown.isDown()` to produce the player's backward movement impulse.

## See Also

| Item | Description |
|------|-------------|
| [ForwardAlias.run()](../ForwardAlias.java/run.md) | Same pattern for forward |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | Reads the key state for movement |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
