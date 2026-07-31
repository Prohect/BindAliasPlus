# SetPitchAlias (src/client/java/com/github/prohect/alias/builtinAlias/SetPitchAlias.java)

Builtin alias that sets the player's camera pitch to an absolute angle. Extends `BuiltinAliasWithDoubleArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SetPitchAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.SetPitchAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `setPitch` — usage: `setPitch\deg` where `deg` is an absolute pitch angle in degrees.

**Behavior:** Sets the player's pitch to the exact value specified by the argument. Unlike `PitchAlias` which applies a relative rotation (`current + delta`), this alias is absolute.

**Valid range:** Minecraft enforces pitch in the range [-90, 90]. Negative values look up, positive values look down. Values outside this range are clamped by the game engine.

**Argument resolution:** The argument is resolved through `VarAlias.resolveDouble()`, supporting both literal numbers and variable names.

**Requirements:** `mc.player` must be non-null. Logs a warning if player is null.

**Difference from PitchAlias:** `setPitch` is absolute (sets to a specific angle), `pitch` is relative (adds/subtracts from current angle).

**No screen suppression:** This is a camera setting, not a game input — works on any screen.

## See Also

| Item | Description |
|------|-------------|
| [PitchAlias](../PitchAlias.java/PitchAlias.md) | Relative pitch rotation |
| [SetYawAlias](../SetYawAlias.java/SetYawAlias.md) | Absolute yaw setter |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system for argument resolution |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Base class for double-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
