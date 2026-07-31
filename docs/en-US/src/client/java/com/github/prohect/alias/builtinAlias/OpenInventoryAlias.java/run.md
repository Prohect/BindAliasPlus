# run method (src/client/java/com/github/prohect/alias/builtinAlias/OpenInventoryAlias.java)

Parses the +/- boolean args and opens or closes the player inventory screen.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.OpenInventoryAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to open inventory, `"0"` to close the currently open inventory/container screen |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag` (true for "1", false for "0").
2. If a text-input screen is open AND flag is true (opening), return immediately — don't open inventory while typing.
3. If `mc.player` is null, return immediately.
4. **Open (flag=true):** If no screen is currently open (`!isUnderAnyScreen()`), calls `mc.setScreen(new InventoryScreen(mc.player))`.
5. **Close (flag=false):** If a container screen is currently open (`isInContainerScreen()`), calls `getCurrentScreen().close()`.

**Side effects:**
- Creates and sets a new `InventoryScreen` on the client (on open).
- Closes the current screen (on close, when on a container screen).

**Screen suppression:** Opening is suppressed when `isUnderTextInputScreen()` is true. Closing is never suppressed.

**Edge cases:**
- If flag is true but any screen is already open, does nothing (silently).
- If flag is false but the current screen is not a container screen (e.g., pause menu), does nothing.

## See Also

| Item | Description |
|------|-------------|
| [OpenInventoryAlias](OpenInventoryAlias.md) | Class overview |
| [ToggleInventoryAlias](../ToggleInventoryAlias.java/ToggleInventoryAlias.md) | One-shot toggle alternative |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
