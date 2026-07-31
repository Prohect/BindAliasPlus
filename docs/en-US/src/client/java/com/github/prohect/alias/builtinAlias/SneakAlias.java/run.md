# run method (src/client/java/com/github/prohect/alias/builtinAlias/SneakAlias.java)

Parses +/- boolean args and presses or releases the sneak key (Shift).

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SneakAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to sneak (press Shift), `"0"` to stop (release Shift) |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag` (true for "1", false for "0").
2. If a text-input screen is open AND flag is true, return immediately.
3. Get the vanilla `sneakKey` keybinding.
4. Call `key.setPressed(flag)` to press or release the key.
5. If pressing (flag=true), increment `timesPressed` so the game registers the initial press event.

**Return value:** `this` (fluent return).

**Side effects:** The player crouches (sneaks) while the key is held. Sneaking prevents falling off block edges and reduces the player's hitbox height. The `KeyboardInputMixin` picks up the keybinding state each tick for window-focus-independent operation.

**Screen suppression:** Press is suppressed on text-input screens. Release is never suppressed.

## See Also

| Item | Description |
|------|-------------|
| [SneakAlias](SneakAlias.md) | Class overview |
| [SprintAlias](../SprintAlias.java/run.md) | Sprint key |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | Reapply after screen transitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
