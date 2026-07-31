# run method (src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java)

Sends a `SWAP_ITEM_WITH_OFFHAND` packet to the server to swap main hand and offhand items.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SwapHandAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. If `isUnderTextInputScreen()` is true, return immediately.
2. Get the network handler via `mc.getNetworkHandler()`.
3. If null, log a warning and return.
4. Send `PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN)` to the server. (Yarn: `PlayerActionC2SPacket`; Mojang: `ServerboundPlayerActionPacket`)

**Return value:** `this` (fluent return).

**Side effects:** Swaps the main hand and offhand items via server packet. The swap is handled server-side and the client inventory updates accordingly.

**Screen suppression:** Cancelled on text-input screens.

**Why not keybinding:** The earlier keybinding-based approach (commented out in source) was replaced with a direct packet send for reliability — it avoids keybinding state conflicts and polling delays.

## See Also

| Item | Description |
|------|-------------|
| [SwapHandAlias](SwapHandAlias.md) | Class overview |
| [SwapSlotAlias](../SwapSlotAlias.java/run.md) | Swap any two slots |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
