# run method (src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java)

Parses +/- boolean args and presses or releases the strafe-right key (D).

## Syntax

```java
public com.github.prohect.alias.builtinAlias.RightAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to strafe right (press D), `"0"` to stop (release D) |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag` (true for "1", false for "0").
2. If a text-input screen is open AND flag is true (pressing), return immediately — don't move while typing.
3. Get the vanilla `rightKey` keybinding.
4. Call `key.setPressed(flag)` to press or release the key.
5. If pressing (flag=true), increment `timesPressed` so the game registers the initial press event.

**Movement injection:** Because the keybinding is set via `setPressed(flag)` directly (not through the vanilla key-press queue), the `KeyboardInputMixin` intercepts it each tick and applies the lateral movement impulse. This works even without window focus.

**Screen suppression:** Press is suppressed on text-input screens. Release is never suppressed.

## See Also

| Item | Description |
|------|-------------|
| [RightAlias](RightAlias.md) | Class overview |
| [LeftAlias](../LeftAlias.java/run.md) | Opposite horizontal movement |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | Reapply after screen transitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
