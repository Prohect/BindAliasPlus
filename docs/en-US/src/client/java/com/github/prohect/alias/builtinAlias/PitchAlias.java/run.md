# run method (src/client/java/com/github/prohect/alias/builtinAlias/PitchAlias.java)

Parses the degree argument and rotates the player's pitch by the specified amount.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.PitchAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Relative pitch angle in degrees (double). Positive = look down, negative = look up. Supports variables. |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — resolves to `flag` (double) using `VarAlias.resolveDouble()`.
2. If `mc.player` is null, log a warning and return.
3. Set player pitch: `player.setXRot((float) flag + player.getXRot())`.

**Return value:** `this` (fluent return).

**Side effects:** Mutates the local player's X-rotation (pitch). The rotation is relative, not absolute.

**Error handling:** Logs `"[pitch]Player is null"` if the player is not available.

## See Also

| Item | Description |
|------|-------------|
| [PitchAlias](PitchAlias.md) | Class overview |
| [SetPitchAlias](../SetPitchAlias.java/run.md) | Absolute pitch setter |
| [YawAlias](../YawAlias.java/run.md) | Relative yaw rotation |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
