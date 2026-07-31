# run method (src/client/java/com/github/prohect/alias/builtinAlias/ToggleInventoryAlias.java)

Toggles the inventory screen: open if closed, close if open.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ToggleInventoryAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. If `isUnderTextInputScreen()` is true, return immediately (suppress on text input).
2. If `mc.player` is null, return immediately.
3. **If a container screen is open (`isInContainerScreen()`):** Call `currentScreen.onClose()` to close it.
4. **Else if no screen is open (`!isUnderAnyScreen()`):** Send `sendOpenInventory()` packet and set the screen to a new `InventoryScreen`.
5. **Else (non-container screen is open):** Do nothing (return silently).

**Return value:** `this` (fluent return).

**Side effects:** Opens or closes the player inventory screen. When opening, sends an `OpenInventory` packet to the server. When closing, fires the screen's `onClose()` handler.

**Screen suppression:** Entirely suppressed on text-input screens.

**Edge case:** If a non-container screen is already open (pause menu, settings, etc.), this alias does nothing — it won't close that screen nor attempt to open inventory over it.

## See Also

| Item | Description |
|------|-------------|
| [ToggleInventoryAlias](ToggleInventoryAlias.md) | Class overview |
| [OpenInventoryAlias](../OpenInventoryAlias.java/run.md) | Switch-based open/close |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
