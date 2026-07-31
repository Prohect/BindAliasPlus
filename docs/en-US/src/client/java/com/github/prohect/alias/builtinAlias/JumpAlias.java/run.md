# run method (src/client/java/com/github/prohect/alias/builtinAlias/JumpAlias.java)

Handles `+jump` (press) and `-jump` (release) by manipulating the vanilla jump key binding.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.JumpAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+jump`), `"0"` for release (`-jump`) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only, text-input only):** If `flag` is true and a text-input screen is open, returns immediately. Jump works on non-text screens. Release events always process.
3. Retrieves `MinecraftClient.getInstance().options.jumpKey` and calls `setPressed(flag)` plus `timesPressed++` on press.

Holding `+jump` keeps the player jumping repeatedly on the ground (matching vanilla spacebar hold behavior) and swimming upward in water.

## See Also

| Item | Description |
|------|-------------|
| [SneakAlias.run()](../SneakAlias.java/run.md) | Same pattern for sneak |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
