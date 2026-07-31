# SetPitchAlias (src/client/java/com/github/prohect/alias/builtinAlias/SetPitchAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SetPitchAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.SetPitchAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that sets the player's pitch to an absolute value (in degrees).

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()` into `Alias.aliasesWithArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Requires `Minecraft.getInstance().player` to be non-null.

Key collaborators: extends [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) which provides `parseArgs()` for double parsing and `flag` field. The `flag` value is assigned directly via `player.setXRot((float)flag)` (absolute, not relative).

The `parseArgs()` method supports variable resolution via [VarAlias](../VarAlias.java/VarAlias.md) in addition to direct double parsing.

## See Also

| Item                                                                                              | Description                                   |
| ------------------------------------------------------------------------------------------------- | --------------------------------------------- |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Parent class providing double arg parsing     |
| [PitchAlias](../PitchAlias.java/PitchAlias.md)                                                    | Relative pitch adjustment                     |
| [SetYawAlias](../SetYawAlias.java/SetYawAlias.md)                                                 | Sets absolute yaw                             |
| [YawAlias](../YawAlias.java/YawAlias.md)                                                          | Relative yaw adjustment                       |
| [run](run.md)                                                                                     | The `run` method that sets the player's pitch |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
