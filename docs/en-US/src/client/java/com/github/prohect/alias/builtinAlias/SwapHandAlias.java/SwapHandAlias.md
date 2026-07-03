# SwapHandAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SwapHandAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.SwapHandAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to swap the main hand and offhand items. Registered as `swapHand`.

**Purpose**: Sends a `ServerboundPlayerActionPacket(SWAP_ITEM_WITH_OFFHAND)` to the server, mirroring vanilla's offhand swap key (F by default) but through the network layer directly rather than through `KeyMapping`.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Sends a network packet.

## See Also

| Item                                                                                     | Description                         |
| ---------------------------------------------------------------------------------------- | ----------------------------------- |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md)                                  | Swaps items between arbitrary slots |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class                        |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
