# run method (src/client/java/com/github/prohect/alias/builtinAlias/SetPitchAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                                                          |
| ------ | -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Absolute pitch value in degrees (e.g., `"0"` for horizontal, `"-90"` for straight up, `"90"` for straight down). Parsed as double via `parseArgs()`. |

## Remarks

Sets the player's pitch (vertical rotation) to an absolute value, replacing the current pitch entirely.

Algorithm:

1. Calls `parseArgs(args)` to parse the args string into the inherited `flag` field as a `double`.
2. Obtains `Minecraft.getInstance().player`.
3. If player is null (not in a world), logs a warning and returns early.
4. Calls `player.setXRot((float)flag)` to set the player's X rotation to the absolute value.

Side effects: replaces the player's X rotation (pitch). The new pitch is clamped by the game engine (typically -90 to +90).

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"setPitch"` matches an `AliasRecord`.

Error handling: if player is null, logs a warning via `BindAliasClient.LOGGER` and returns early. If `args` is not a valid double, `parseArgs()` logs an error and `flag` remains 0 (sets pitch to 0/horizontal). Supports variable resolution via `VarAlias.resolveDouble()`.

## See Also

| Item                                                                                       | Description                               |
| ------------------------------------------------------------------------------------------ | ----------------------------------------- |
| [SetPitchAlias](SetPitchAlias.md)                                                          | Owning class                              |
| [BuiltinAliasWithDoubleArgs.parseArgs](../../BuiltinAliasWithDoubleArgs.java/parseArgs.md) | Parses args into `flag` as double         |
| [PitchAlias.run](../PitchAlias.java/run.md)                                                | Relative pitch adjustment                 |
| [SetYawAlias.run](../SetYawAlias.java/run.md)                                              | Sets absolute yaw                         |
| [VarAlias](../VarAlias.java/VarAlias.md)                                                   | Variable resolution used by `parseArgs()` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
