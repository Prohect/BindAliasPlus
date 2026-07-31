# DropAlias (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.DropAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DropAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to hold-to-repeat item dropping. Registered as `builtinDrop`.

**Purpose**: Simulates continuous dropping while a key is held, matching vanilla behavior. Supports both 3D game dropping (via `keyDrop.clickCount++`) and container-screen dropping (via `slotClicked(…, THROW)`). Also supports Ctrl+drop (whole stack) in container screens.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup. Instance field `ticksHeld` tracks the hold duration per-instance.

**Thread safety**: Not thread-safe (render-thread only). `ticksHeld` is mutated from the render thread without synchronization.

**Key collaborators**: Called from a mixin (`MinecraftClientMixin`) every client tick via `tickDrop()` while `flag` is true. `reapplyToGameKeyMapping()` is called when the cursor is re-locked to maintain `KeyMapping` state.

## See Also

| Item                                                                                                 | Description                                     |
| ---------------------------------------------------------------------------------------------------- | ----------------------------------------------- |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent class providing `flag` and `parseArgs()` |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md)                                                 | Can reapply drop state after screen transitions |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
