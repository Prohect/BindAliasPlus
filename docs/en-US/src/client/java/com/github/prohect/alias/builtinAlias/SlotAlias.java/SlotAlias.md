# SlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java)

Builtin alias that selects a hotbar slot (1-9). Extends `BuiltinAliasWithArgs` directly (NOT `BuiltinAliasWithIntegerArgs`), giving it full control over argument resolution.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SlotAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `slot` — usage: `slot\N` where N is a hotbar slot number (1-9) or a variable name resolving to 1-9.

**Behavior:** Selects the specified hotbar slot by calling `inventory.selectedSlot = i - 1)` and sending a `UpdateSelectedSlotC2SPacket` to the server to synchronize the change.

**Argument resolution:** The argument is resolved via `VarAlias.resolveInt()`, supporting both literal numbers (1-9) and variable names. Invalid or out-of-range values are rejected with a warning log.

**Why it extends BuiltinAliasWithArgs directly:** Unlike `BuiltinAliasWithIntegerArgs` which calls `parseArgs(args)` automatically, `SlotAlias` bypasses automatic parsing to get finer control over argument validation and to use `VarAlias.resolveInt()` directly. It does NOT use `this.flag` for the slot number.

**No screen suppression:** Works on any screen — hotbar selection is valid regardless of open GUI.

**Implementation note:** The commented-out code at the top of `run()` shows an earlier approach using `KeyBinding` keybindings to simulate hotbar keypresses. The current implementation uses direct inventory manipulation + server packet for reliability.

**Edge cases:**
- null args or non-integer args: logged as warning
- args outside 1-9 range: logged as warning
- null player or null inventory: logged as warning
- Failed server packet: logged as error (but slot still changes on client side)

## See Also

| Item | Description |
|------|-------------|
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system used for argument resolution |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | Swap items between slots |
| [PickItemAlias](../PickItemAlias.java/PickItemAlias.md) | Pick-block to select slot matching target |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Direct base class |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
