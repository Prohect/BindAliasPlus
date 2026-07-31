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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
