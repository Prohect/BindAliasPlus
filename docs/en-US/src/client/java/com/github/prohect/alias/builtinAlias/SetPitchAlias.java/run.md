# run method (src/client/java/com/github/prohect/alias/builtinAlias/SetPitchAlias.java)

Parses the degree argument and sets the player's pitch to an absolute angle.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SetPitchAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Absolute pitch angle in degrees (double). Supports variables. |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — resolves to `flag` (double) using `VarAlias.resolveDouble()`.
2. If `mc.player` is null, log a warning and return.
3. Set player pitch: `player.setXRot((float) flag)`.

**Return value:** `this` (fluent return).

**Side effects:** Mutates the local player's X-rotation (pitch) to the absolute value. The vanilla game engine clamps the result to [-90, 90].

**No screen suppression:** Works on any screen.

**Difference from PitchAlias:** This sets the absolute pitch; `pitch\deg` adds to the current pitch.

**Example:** `setPitch\-45` sets pitch to look up at 45 degrees. `setPitch\0` resets pitch to horizontal.

## See Also

| Item | Description |
|------|-------------|
| [SetPitchAlias](SetPitchAlias.md) | Class overview |
| [PitchAlias](../PitchAlias.java/run.md) | Relative pitch rotation |
| [SetYawAlias](../SetYawAlias.java/run.md) | Absolute yaw setter |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
