# run method (src/client/java/com/github/prohect/alias/builtinAlias/SetYawAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                                                      |
| ------ | -------- | ------------------------------------------------------------------------------------------------------------------------------------------------ |
| `args` | `String` | Absolute yaw value in degrees (e.g., `"0"` for south, `"90"` for west, `"180"` for north, `"-90"` for east). Parsed as double via `parseArgs()`. |

## Remarks

Sets the player's yaw (horizontal rotation) to an absolute value, replacing the current yaw entirely.

Algorithm:

1. Calls `parseArgs(args)` to parse the args string into the inherited `flag` field as a `double`.
2. Obtains `Minecraft.getInstance().player`.
3. If player is null (not in a world), logs a warning and returns early.
4. Calls `player.setYRot((float)flag)` to set the player's Y rotation to the absolute value.

Side effects: replaces the player's Y rotation (yaw). Yaw values outside 0-360 are normalized by the game engine.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"setYaw"` matches an `AliasRecord`.

Error handling: if player is null, logs a warning via `BindAliasPlusClient.LOGGER` and returns early. If `args` is not a valid double, `parseArgs()` logs an error and `flag` remains 0 (sets yaw to 0/south). Supports variable resolution via `VarAlias.resolveDouble()`.

## See Also

| Item                                                                                       | Description                               |
| ------------------------------------------------------------------------------------------ | ----------------------------------------- |
| [SetYawAlias](SetYawAlias.md)                                                              | Owning class                              |
| [BuiltinAliasWithDoubleArgs.parseArgs](../../BuiltinAliasWithDoubleArgs.java/parseArgs.md) | Parses args into `flag` as double         |
| [YawAlias.run](../YawAlias.java/run.md)                                                    | Relative yaw adjustment                   |
| [SetPitchAlias.run](../SetPitchAlias.java/run.md)                                          | Sets absolute pitch                       |
| [VarAlias](../VarAlias.java/VarAlias.md)                                                   | Variable resolution used by `parseArgs()` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
