# run method (src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java)

Handles `+attack` (press) and `-attack` (release) by manipulating the vanilla attack key binding.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.AttackAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+attack`), `"0"` for release (`-attack`), as parsed by `parseArgs(args)` |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag` (true for `"1"`, false for `"0"`).
2. **Screen suppression (press only):** If `flag` is true and a text-input screen (chat, sign, book, command block) is open, returns immediately — the press is discarded so the attack key is never injected while the player is typing. Release events are still processed so the key does not stick.
3. Retrieves `MinecraftClient.getInstance().options.attackKey` and calls:
   - `setPressed(flag)` — sets the binding as held (press) or released
   - `timesPressed++` (press only) — increments the click counter so that Minecraft's `wasPressed()` / attack-tap logic fires (Yarn: `wasPressed()`; Mojang: `consumeClick()`)

**Additional screen suppression:** The `AttackAlias` is registered with `addToScreenBlackList()` in `BindAliasClient`, which causes `UserAlias.run()` to suppress it on **all** screens — not just text-input screens. This double guard is intentional for safety: attack / use actions have no legitimate purpose outside the 3D world.

## See Also

| Item | Description |
|------|-------------|
| [Alias.isUnderTextInputScreen()](../../Alias.java/isUnderTextInputScreen.md) | Screen check used here |
| [UseAlias.run()](../UseAlias.java/run.md) | Same pattern for right-click |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
