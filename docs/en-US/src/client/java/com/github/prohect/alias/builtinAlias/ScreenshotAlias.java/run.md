# run method (src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java)

Parses +/- args and triggers a screenshot on the press event using the vanilla F2 keypress path.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ScreenshotAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to take a screenshot, `"0"` is a no-op |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag`.
2. If flag is false (release), return immediately (no-op).
3. If `isUnderTextInputScreen()` is true, return immediately.
4. If `mc.player` is null, return immediately.
5. Call `mc.handleGlobalKeyPress(mc.options.keyScreenshot.key, false)` — triggers the vanilla screenshot pipeline (same as pressing F2).

**Return value:** `this` (fluent return).

**Side effects:** Takes a screenshot and saves it to the Minecraft screenshots directory. Uses the vanilla timestamp-based filename.

**Screen suppression:** Cancelled when a text-input screen is open.

**Note:** Only the `+screenshot` (flag=1) form takes action. `-screenshot` is a no-op.

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotAlias](ScreenshotAlias.md) | Class overview |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
