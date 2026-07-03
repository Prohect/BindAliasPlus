# VarAlias (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.VarAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.VarAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to store and retrieve in-game variables. Registered as `var`.

**Purpose**: Provides a global variable system accessible from alias definitions. Variables store `Number` values (Integer or Double) keyed by string name. Sources for variable values include current hotbar slot, item counts, player pitch/yaw, or literal numbers. Other aliases (e.g. [SwapSlotAlias](SwapSlotAlias.java/SwapSlotAlias.md), [SlotAlias](SlotAlias.java/SlotAlias.md)) resolve variable names to their stored values via the static `resolveValue()` / `resolveInt()` / `resolveDouble()` methods.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup. Variables persist in the static `VARIABLES` map across world joins/disconnects. Autoloaded variables (from config file) are tracked separately in `AUTOLOADED_VARIABLES` for selective unloading.

**Thread safety**: Not thread-safe (render-thread only). The static `VARIABLES` and `AUTOLOADED_VARIABLES` are mutated from the render thread without synchronization.

**Key collaborators**: Heavily depended on by [SwapSlotAlias](SwapSlotAlias.java/SwapSlotAlias.md), [SlotAlias](SlotAlias.java/SlotAlias.md), and any alias that accepts numeric arguments. Autoloaded variable tracking integrates with [UnloadCFGVarsAlias](UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) and [UnloadCFGAllAlias](UnloadCFGAllAlias.java/UnloadCFGAllAlias.md).

## See Also

| Item                                                                            | Description                            |
| ------------------------------------------------------------------------------- | -------------------------------------- |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md)                         | Uses `resolveInt()` for slot args      |
| [SlotAlias](../SlotAlias.java/SlotAlias.md)                                     | Uses `resolveInt()` for slot selection |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md)          | Removes autoloaded variables           |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
