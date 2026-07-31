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
4. **Open (flag=true):** If no screen is currently open (`!isUnderAnyScreen()`), send `sendOpenInventory()` packet to the server and set the client screen to a new `InventoryScreen`.
5. **Close (flag=false):** If a container screen is currently open (`isInContainerScreen()`), call `onClose()` on the current screen.

**Side effects:**
- Sends `ServerboundOpenInventory` packet to the server (on open).
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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
