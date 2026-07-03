# run method (src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                           |
| ------ | -------- | --------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Relative yaw delta in degrees (e.g., `"90"` to turn right, `"-90"` to turn left). Parsed as double via `parseArgs()`. |

## Remarks

Adjusts the player's yaw (horizontal rotation) by adding the parsed double value to the current yaw.

Algorithm:

1. Calls `parseArgs(args)` to parse the args string into the inherited `flag` field as a `double`.
2. Obtains `Minecraft.getInstance().player`.
3. If player is null (not in a world), logs a warning and returns early.
4. Calls `player.setYRot((float)(player.getYRot() + flag))` to add the delta to the current rotation.

Side effects: modifies the player's Y rotation (yaw). Yaw values outside 0-360 are normalized by the game engine.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"yaw"` matches an `AliasRecord`.

Error handling: if player is null, logs a warning via `BindAliasPlusClient.LOGGER` and returns early. If `args` is not a valid double, `parseArgs()` logs an error and `flag` remains 0 (no change). Supports variable resolution via `VarAlias.resolveDouble()`.

## See Also

| Item                                                                                       | Description                               |
| ------------------------------------------------------------------------------------------ | ----------------------------------------- |
| [YawAlias](YawAlias.md)                                                                    | Owning class                              |
| [BuiltinAliasWithDoubleArgs.parseArgs](../../BuiltinAliasWithDoubleArgs.java/parseArgs.md) | Parses args into `flag` as double         |
| [SetYawAlias.run](../SetYawAlias.java/run.md)                                              | Sets absolute yaw                         |
| [PitchAlias.run](../PitchAlias.java/run.md)                                                | Relative pitch adjustment                 |
| [VarAlias](../VarAlias.java/VarAlias.md)                                                   | Variable resolution used by `parseArgs()` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
