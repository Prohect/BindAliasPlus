# SlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SlotAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to select a hotbar slot. Registered as `slot`.

**Purpose**: Switches the player's active hotbar slot to the specified number (1-9). Also sends a `ServerboundSetCarriedItemPacket` to sync the change with the server. Accepts variable names resolved via `VarAlias.resolveInt()`.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Interacts with the player's inventory and network handler.

**Key collaborators**: Uses `[VarAlias.resolveInt](VarAlias.java/resolveInt.md)` to support variable references. Sends packets via `Minecraft.getInstance().getConnection()`.

## See Also

| Item                                                                            | Description                                    |
| ------------------------------------------------------------------------------- | ---------------------------------------------- |
| [VarAlias.resolveInt](../VarAlias.java/resolveInt.md)                           | Variable resolution for slot number            |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md)                         | Swaps items between slots (not just selection) |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                                   |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
