# PitchAlias (src/client/java/com/github/prohect/alias/builtinAlias/PitchAlias.java)

Builtin alias that rotates the player's camera pitch by a relative angle. Extends `BuiltinAliasWithDoubleArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.PitchAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.PitchAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `pitch` — usage: `pitch\deg` where `deg` is a relative pitch angle in degrees (double-precision).

**Behavior:** Parses the argument into `flag` (a double) via `parseArgs(args)`, then sets the player's pitch to `player.getXRot() + flag`. Positive values look down, negative values look up.

**Variable resolution:** The argument is resolved through `VarAlias.resolveDouble()`, so it can be a literal number or a variable name that stores a number.

**Requirements:** `mc.player` must be non-null. Logs a warning if player is null.

**Clamping:** Unlike `setPitch`, this alias does not clamp the result — the vanilla pitch range (-90 to 90) is enforced by the game engine after the rotation is applied.

## See Also

| Item | Description |
|------|-------------|
| [SetPitchAlias](../SetPitchAlias.java/SetPitchAlias.md) | Absolute pitch setter |
| [YawAlias](../YawAlias.java/YawAlias.md) | Relative yaw rotation (horizontal) |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system used for argument resolution |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Base class for double-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
