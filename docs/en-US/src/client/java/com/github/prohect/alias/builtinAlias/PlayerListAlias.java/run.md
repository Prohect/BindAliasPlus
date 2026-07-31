# run method (src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java)

Parses +/- boolean args and presses or releases the player-list (Tab) key.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.PlayerListAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to show player list (press Tab), `"0"` to hide (release Tab) |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag` (true for "1", false for "0").
2. If a text-input screen is open AND flag is true, return immediately — don't show overlay while typing.
3. Get the vanilla `keyPlayerList` keybinding.
4. Call `key.setDown(flag)` to press or release the key.
5. If pressing (flag=true), increment `key.clickCount` so the game registers the initial press event.

**Side effects:** Shows or hides the online-player overlay. The keybinding state (`isDown`, `clickCount`) is modified.

**Screen suppression:** Press is suppressed when `isUnderTextInputScreen()` is true.

## See Also

| Item | Description |
|------|-------------|
| [PlayerListAlias](PlayerListAlias.md) | Class overview |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | Reapply held keys after screen transitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
