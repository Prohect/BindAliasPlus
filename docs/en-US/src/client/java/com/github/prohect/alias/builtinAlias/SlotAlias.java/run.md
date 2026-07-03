# run method (src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                             |
| ------ | -------- | ----------------------------------------------------------------------- |
| `args` | `String` | A hotbar slot number from 1-9, or a variable name that resolves to 1-9. |

## Remarks

Selects the specified hotbar slot and syncs the change with the server.

**Algorithm**:

1. Resolve `args` via `VarAlias.resolveInt()`. If null, log warning and return.
2. Validate 1 ≤ i ≤ 9. If out of range, log warning and return.
3. Guard against null player/inventory.
4. Call `inventory.setSelectedSlot(i - 1)` (converts 1-based to 0-based).
5. Send `ServerboundSetCarriedItemPacket(i - 1)` to sync with server.
6. Catch and log any exceptions from the network call.

**Side effects**: Changes the player's selected hotbar slot (client-side) and sends a network packet (server-side).

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                                  | Description         |
| ----------------------------------------------------- | ------------------- |
| [VarAlias.resolveInt](../VarAlias.java/resolveInt.md) | Variable resolution |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
