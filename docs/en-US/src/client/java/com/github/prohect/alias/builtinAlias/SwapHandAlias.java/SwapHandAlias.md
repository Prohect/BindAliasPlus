# SwapHandAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java)

One-shot alias that swaps the main hand and offhand items via the vanilla SWAP action packet. Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SwapHandAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.SwapHandAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `swapHand` (internal, exposed as `swapHand`).

**Behavior:** Sends a `PlayerActionC2SPacket` with action `SWAP_ITEM_WITH_OFFHAND` to the server. This swaps the player's main hand held item with the offhand item. (Yarn: `PlayerActionC2SPacket`; Mojang: `ServerboundPlayerActionPacket`)

**Why network packet instead of keybinding:** The commented-out code shows an earlier approach using the `keySwapOffhand` keybinding. The current implementation sends the network packet directly for reliability — it bypasses the keybinding polling cycle and works regardless of keybinding state.

**Parameters:** The packet is sent with `BlockPos.ORIGIN` and `Direction.DOWN` as placeholder values (they're ignored by the server for SWAP_ITEM_WITH_OFFHAND actions). (Yarn: `BlockPos.ORIGIN`; Mojang: `BlockPos.ZERO`)

**Screen suppression:** Cancelled when `Alias.isUnderTextInputScreen()` returns true.

**Requirements:** The network handler (`mc.getNetworkHandler()`) must be non-null. Logs a warning if null.

**Note:** After the swap, the previously-selected hotbar slot still holds the same physical items (now in a different hand). The server handles the item swap logic.

## See Also

| Item | Description |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | Swap any two inventory/container slots |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | Select a hotbar slot |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Base class for one-shot aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
