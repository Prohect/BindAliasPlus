# run method (src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java)

Parses the degree argument and rotates the player's yaw by the specified amount.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.YawAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Relative yaw angle in degrees (double). Positive = turn left, negative = turn right. Supports variables. |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — resolves to `flag` (double) using `VarAlias.resolveDouble()`.
2. If `mc.player` is null, log a warning and return.
3. Set player yaw: `player.setYRot((float) (player.getYRot() + flag))`.

**Return value:** `this` (fluent return).

**Side effects:** Mutates the local player's Y-rotation (yaw). The rotation is relative, not absolute. Yaw values wrap around automatically.

**No screen suppression:** Works on any screen.

**Example:**
- `yaw\90` — turn 90 degrees left
- `yaw\-90` — turn 90 degrees right
- `yaw\180` — turn around

## See Also

| Item | Description |
|------|-------------|
| [YawAlias](YawAlias.md) | Class overview |
| [SetYawAlias](../SetYawAlias.java/run.md) | Absolute yaw setter |
| [PitchAlias](../PitchAlias.java/run.md) | Relative pitch rotation |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
