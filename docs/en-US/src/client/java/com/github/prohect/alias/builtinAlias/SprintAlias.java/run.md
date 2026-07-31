# run method (src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java)

Parses +/- boolean args and presses or releases the sprint key (Ctrl).

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SprintAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to sprint (press Ctrl), `"0"` to stop (release Ctrl) |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag` (true for "1", false for "0").
2. If a text-input screen is open AND flag is true, return immediately.
3. Get the vanilla `keySprint` keybinding.
4. Call `key.setDown(flag)` to press or release the key.
5. If pressing (flag=true), increment `clickCount`.

**Return value:** `this` (fluent return).

**Side effects:** Enables sprinting when combined with forward movement. The player moves faster but consumes food at an increased rate. Sprinting is subject to vanilla constraints (hunger > 6, not blocked, continuous forward movement).

**Screen suppression:** Press is suppressed on text-input screens. Release is never suppressed.

## See Also

| Item | Description |
|------|-------------|
| [SprintAlias](SprintAlias.md) | Class overview |
| [SneakAlias](../SneakAlias.java/run.md) | Sneak key |
| [ForwardAlias](../ForwardAlias.java/run.md) | Forward movement (required for sprinting) |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | Reapply after screen transitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
