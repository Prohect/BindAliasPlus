# YawAlias (src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java)

Builtin alias that rotates the player's camera yaw by a relative angle. Extends `BuiltinAliasWithDoubleArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.YawAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.YawAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `yaw` — usage: `yaw\deg` where `deg` is a relative yaw angle in degrees (double-precision).

**Behavior:** Parses the argument into `flag` (a double) via `parseArgs(args)`, then sets the player's yaw to `player.getYRot() + flag`. Positive values turn left (toward negative yaw), negative values turn right.

**Variable resolution:** The argument is resolved through `VarAlias.resolveDouble()`, so it can be a literal number or a variable name that stores a number.

**Requirements:** `mc.player` must be non-null. Logs a warning if player is null.

**Yaw wrapping:** Minecraft yaw wraps naturally — values outside [0, 360) or [-180, 180) are automatically normalized by the game engine. This means continuous rotation in one direction works without needing to calculate modulo.

**Difference from SetYawAlias:** `yaw\deg` is relative (adds to current yaw); `setYaw\deg` is absolute.

**No screen suppression:** Works on any screen — it's a camera setting, not a game input.

## See Also

| Item | Description |
|------|-------------|
| [SetYawAlias](../SetYawAlias.java/SetYawAlias.md) | Absolute yaw setter |
| [PitchAlias](../PitchAlias.java/PitchAlias.md) | Relative pitch rotation (vertical) |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system used for argument resolution |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Base class for double-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
