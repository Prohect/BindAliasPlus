# ToggleInventoryAlias (src/client/java/com/github/prohect/alias/builtinAlias/ToggleInventoryAlias.java)

One-shot alias that toggles the player inventory screen — opens it if closed, closes it if open. Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ToggleInventoryAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ToggleInventoryAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `toggleInventory` (internal, exposed as `toggleInventory`).

**Behavior:**
- If a container screen (any `AbstractContainerScreen`) is currently open: closes it via `onClose()`.
- If no screen is currently open: opens the player inventory screen by calling `sendOpenInventory()` (server packet) and creating a new `InventoryScreen`.

**Screen suppression:** Both open and close are suppressed when `Alias.isUnderTextInputScreen()` returns true.

**Requirements:** `mc.player` must be non-null. Returns silently if null.

**Difference from OpenInventoryAlias:** `toggleInventory` is a one-shot toggle — it reverses the current state. `OpenInventoryAlias` uses the `+`/`-` switch pattern for explicit open/close control. ToggleInventoryAlias is simpler and more common for quick inventory access.

**Edge case — any screen open:** If a non-container screen is open (e.g., pause menu, settings), `toggleInventory` does nothing (returns silently) because `isUnderAnyScreen()` returns true but `isInContainerScreen()` returns false, and neither branch executes.

## See Also

| Item | Description |
|------|-------------|
| [OpenInventoryAlias](../OpenInventoryAlias.java/OpenInventoryAlias.md) | Switch-based open/close with +/- pattern |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Base class for one-shot aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
