# run method (src/client/java/com/github/prohect/alias/builtinAlias/SetYawAlias.java)

Parses the degree argument and sets the player's yaw to an absolute angle.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SetYawAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Absolute yaw angle in degrees (double). Supports variables. |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — resolves to `flag` (double) using `VarAlias.resolveDouble()`.
2. If `mc.player` is null, log a warning and return.
3. Set player yaw: `player.setYRot((float) flag)`.

**Return value:** `this` (fluent return).

**Side effects:** Mutates the local player's Y-rotation (yaw) to the absolute value. Values are wrapped automatically by the game engine.

**No screen suppression:** Works on any screen.

**Difference from YawAlias:** This sets the absolute yaw; `yaw\deg` adds to the current yaw.

**Example:**
- `setYaw\0` — face South
- `setYaw\90` — face West
- `setYaw\180` — face North

## See Also

| Item | Description |
|------|-------------|
| [SetYawAlias](SetYawAlias.md) | Class overview |
| [YawAlias](../YawAlias.java/run.md) | Relative yaw rotation |
| [SetPitchAlias](../SetPitchAlias.java/run.md) | Absolute pitch setter |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
