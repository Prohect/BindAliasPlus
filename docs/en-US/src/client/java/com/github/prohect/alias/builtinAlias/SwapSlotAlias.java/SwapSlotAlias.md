# SwapSlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SwapSlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SwapSlotAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to swap items between two inventory slots, including hotbar, offhand, and equipment slots. Registered as `swapSlot`.

**Purpose**: Programmatically move items between any two player inventory slots without manual dragging. Supports both quick hotbar/offhand swaps (via network packets) and full inventory-screen-based swaps (via simulated slot clicks).

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup. No instance state beyond the parent class.

**Thread safety**: Not thread-safe (render-thread only). Interacts with Minecraft's screen system and network handler, which are render-thread-bound.

**Key collaborators**: Uses `VarAlias.resolveInt()` for variable-name resolution in slot arguments. Uses `McScreenHelper` to open/close inventory screens. Sends `ServerboundSetCarriedItemPacket` and `ServerboundPlayerActionPacket` for network-based swaps. Interacts with `Minecraft.gameMode` for screen-based slot clicks.

## See Also

| Item                                                                            | Description                            |
| ------------------------------------------------------------------------------- | -------------------------------------- |
| [VarAlias](../VarAlias.java/VarAlias.md)                                        | Variable resolution for slot arguments |
| [SlotAlias](../SlotAlias.java/SlotAlias.md)                                     | Simple hotbar slot selection           |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
