# run method (src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java)

Resolves the hotbar slot number (1-9) from args, validates it, and sets the selected slot on both client and server.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SlotAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Hotbar slot number 1-9, or a variable name resolving to 1-9 |

## Remarks

**Algorithm:**

1. Call `VarAlias.resolveInt(args)` to resolve the argument. If null (not a valid number or variable), log a warning and return.
2. Validate the resolved integer is in range [1, 9]. If not, log a warning and return.
3. Get `mc.player` and `player.getInventory()`. If either is null, log a warning and return.
4. Call `inventory.selectedSlot = i - 1)` to set the hotbar slot locally.
5. Send `UpdateSelectedSlotC2SPacket(i - 1)` to the server to synchronize.
6. If the packet send throws an exception, log it as an error.

**Return value:** `this` (fluent return).

**Side effects:**
- Changes the selected hotbar slot on the client.
- Sends a network packet to the server to synchronize the selected slot.
- The hotbar visually updates to show the new selection.

**No screen suppression:** Works on any screen. The selected slot changes even while a container or inventory screen is open (though the slot's function depends on the screen context).

## See Also

| Item | Description |
|------|-------------|
| [SlotAlias](SlotAlias.md) | Class overview |
| [VarAlias](../VarAlias.java/resolveInt.md) | Used for argument resolution |
| [SwapSlotAlias](../SwapSlotAlias.java/run.md) | Swap items between slots |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
