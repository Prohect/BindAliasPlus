# run method (src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java)

Handles `+advancements` (press) and `-advancements` (release) by manipulating the vanilla advancements key binding.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.AdvancementsAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+advancements`), `"0"` for release (`-advancements`) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only, text-input only):** If `flag` is true and a text-input screen is open, returns immediately. Release events always process.
3. Retrieves `Minecraft.options.keyAdvancements` and calls `setDown(flag)` plus `clickCount++` on press.

The advancements key is unusual: vanilla's `Gui.java` polls it via `consumeClick()`, and the advancements screen opens when the key is **released** after being pressed — not on the press itself. The `-advancements` form has no toggle effect.

## See Also

| Item | Description |
|------|-------------|
| [PlayerListAlias.run()](../PlayerListAlias.java/run.md) | Similar toggle pattern |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
