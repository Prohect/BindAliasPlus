# SetYawAlias (src/client/java/com/github/prohect/alias/builtinAlias/SetYawAlias.java)

Builtin alias that sets the player's camera yaw to an absolute angle. Extends `BuiltinAliasWithDoubleArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SetYawAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.SetYawAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `setYaw` — usage: `setYaw\deg` where `deg` is an absolute yaw angle in degrees.

**Behavior:** Sets the player's yaw to the exact value specified by the argument. Unlike `YawAlias` which applies a relative rotation (`current + delta`), this alias is absolute.

**Yaw orientation:** In Minecraft:
- 0 = South
- 90 = West
- 180 = North
- 270 = East
Values wrap around (e.g., 360 = 0, -90 = 270).

**Argument resolution:** The argument is resolved through `VarAlias.resolveDouble()`, supporting both literal numbers and variable names.

**Requirements:** `mc.player` must be non-null. Logs a warning if player is null.

**Difference from YawAlias:** `setYaw` is absolute (sets to a specific angle), `yaw` is relative (adds/subtracts from current angle).

**No screen suppression:** This is a camera setting, not a game input — works on any screen.

## See Also

| Item | Description |
|------|-------------|
| [YawAlias](../YawAlias.java/YawAlias.md) | Relative yaw rotation |
| [SetPitchAlias](../SetPitchAlias.java/SetPitchAlias.md) | Absolute pitch setter |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system for argument resolution |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Base class for double-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
