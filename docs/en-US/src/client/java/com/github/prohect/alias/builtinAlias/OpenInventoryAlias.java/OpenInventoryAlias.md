# OpenInventoryAlias (src/client/java/com/github/prohect/alias/builtinAlias/OpenInventoryAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.OpenInventoryAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.OpenInventoryAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to open or close the inventory screen. Registered as `builtinOpenInventory`.

**Purpose**: Programmatically opens the player inventory (both client screen and server packet via `sendOpenInventory()`) or closes any open container screen. Respects text input screens (does not open if one is active). When closing, only acts if a container screen is open.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Interacts with the screen system and network handler.

## See Also

| Item                                                                                                 | Description                                     |
| ---------------------------------------------------------------------------------------------------- | ----------------------------------------------- |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent class providing `flag` and `parseArgs()` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
