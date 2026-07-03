# run method (src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description |
| ------ | -------- | ----------- |
| `args` | `String` | Ignored.    |

## Remarks

Sends an offhand swap packet to the server.

**Algorithm**:

1. Guard against null network handler; log warning if null.
2. Send `ServerboundPlayerActionPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN)`.

**Side effects**: Sends a network packet. The server swaps the player's main hand and offhand items.

**Callers**: Invoked by the alias dispatch system.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
