# YawAlias (src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.YawAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.YawAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that adjusts the player's yaw by a relative amount (in degrees).

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()` into `Alias.aliasesWithArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Requires `Minecraft.getInstance().player` to be non-null.

Key collaborators: extends [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) which provides `parseArgs()` for double parsing and `flag` field. The `flag` value is added to the player's current yaw via `player.setYRot((float)(player.getYRot() + flag))`.

The `parseArgs()` method supports variable resolution via [VarAlias](../VarAlias.java/VarAlias.md) in addition to direct double parsing.

## See Also

| Item                                                                                              | Description                                    |
| ------------------------------------------------------------------------------------------------- | ---------------------------------------------- |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Parent class providing double arg parsing      |
| [SetYawAlias](../SetYawAlias.java/SetYawAlias.md)                                                 | Sets absolute yaw instead of relative          |
| [PitchAlias](../PitchAlias.java/PitchAlias.md)                                                    | Same pattern for pitch rotation                |
| [SetPitchAlias](../SetPitchAlias.java/SetPitchAlias.md)                                           | Sets absolute pitch                            |
| [run](run.md)                                                                                     | The `run` method that adjusts the player's yaw |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
