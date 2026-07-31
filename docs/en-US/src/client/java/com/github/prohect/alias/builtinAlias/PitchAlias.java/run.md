# run method (src/client/java/com/github/prohect/alias/builtinAlias/PitchAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                          |
| ------ | -------- | -------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Relative pitch delta in degrees (e.g., `"90"` to look down, `"-90"` to look up). Parsed as double via `parseArgs()`. |

## Remarks

Adjusts the player's pitch (vertical rotation) by adding the parsed double value to the current pitch.

Algorithm:

1. Calls `parseArgs(args)` to parse the args string into the inherited `flag` field as a `double`.
2. Obtains `Minecraft.getInstance().player`.
3. If player is null (not in a world), logs a warning and returns early.
4. Sets the player's X rotation to `(float)flag + player.getXRot()`, effectively adding the delta to the current pitch.

Side effects: modifies the player's X rotation (pitch). The new pitch is clamped by the game engine (typically -90 to +90).

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"pitch"` matches an `AliasRecord`.

Error handling: if player is null, logs a warning via `BindAliasClient.LOGGER` and returns early. If `args` is not a valid double, `parseArgs()` logs an error and `flag` remains 0 (no change). Supports variable resolution via `VarAlias.resolveDouble()`.

## See Also

| Item                                                                                       | Description                               |
| ------------------------------------------------------------------------------------------ | ----------------------------------------- |
| [PitchAlias](PitchAlias.md)                                                                | Owning class                              |
| [BuiltinAliasWithDoubleArgs.parseArgs](../../BuiltinAliasWithDoubleArgs.java/parseArgs.md) | Parses args into `flag` as double         |
| [SetPitchAlias.run](../SetPitchAlias.java/run.md)                                          | Sets absolute pitch                       |
| [YawAlias.run](../YawAlias.java/run.md)                                                    | Relative yaw adjustment                   |
| [VarAlias](../VarAlias.java/VarAlias.md)                                                   | Variable resolution used by `parseArgs()` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
