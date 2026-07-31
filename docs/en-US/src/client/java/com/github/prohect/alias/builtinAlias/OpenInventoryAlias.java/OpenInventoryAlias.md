# OpenInventoryAlias (src/client/java/com/github/prohect/alias/builtinAlias/OpenInventoryAlias.java)

Builtin switch alias (`+openInventory` / `-openInventory`) that opens or closes the player inventory screen. Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.OpenInventoryAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.OpenInventoryAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinOpenInventory` (internal, exposed via `+openInventory` / `-openInventory`).

**Behavior:**
- `+openInventory` (flag=1): Opens the player inventory screen by calling `mc.setScreen(new InventoryScreen(mc.player))`. Blocked when a text-input screen is open. Blocked when any screen is already open.
- `-openInventory` (flag=0): Closes the current screen only if it is a container screen (`isInContainerScreen()`). This is a no-op if no container screen is open.

**Screen suppression:** The press event (`+openInventory`) is cancelled when `Alias.isUnderTextInputScreen()` returns true. The release event is never suppressed.

**Difference from ToggleInventoryAlias:** This alias provides separate open/close control via the +/- pattern, whereas `toggleInventory` toggles between open and closed on each invocation.

## See Also

| Item | Description |
|------|-------------|
| [ToggleInventoryAlias](../ToggleInventoryAlias.java/ToggleInventoryAlias.md) | One-shot toggle that does not use the +/- pattern |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |
| [McScreenHelper](../../../util/McScreenHelper.java/McScreenHelper.md) | Screen management helper used by this alias |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
