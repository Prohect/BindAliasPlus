# directionOf method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
static String directionOf(LocalPlayer p, double dx, double dy, double dz)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `p` | `LocalPlayer` | The local player (listener) |
| `dx` | `double` | X delta: target minus player position |
| `dy` | `double` | Y delta: target minus player position |
| `dz` | `double` | Z delta: target minus player position |

## Return value

Directional string in one of two forms:
- Point-blank (< 0.31m): `"here D.Dm"`
- Directional: `"yaw±N pitch±N D.Dm"` — yaw/pitch of the target **relative to the player's current view**, each rounded to the nearest 20° step, plus the 3D distance

If the horizontal distance is below 0.5m, the yaw component is omitted (only pitch shown). At point-blank distances, the angles are meaningless and collapse to `"here D.Dm"`.

## Remarks

Uses Minecraft's yaw convention (0=south/+Z, 90=west/-X, ±180=north/-Z, -90=east/+X) and pitch convention (-90=up, 0=horizon, 90=down). Computes the absolute yaw/pitch of the target, subtracts the player's current rotation to get relative angles, normalizes to [-180, 180], and rounds to the nearest 20° step. Also used by `GameStateCollector.playersJson` for formatting nearby player directions.

## See Also

| Item | Description |
|------|-------------|
| [GameStateCollector.playersJson](GameStateCollector.java/playersJson.md) | The other caller |
| [clamp](clamp.md) | 20° step rounding |
| [normalize180](normalize180.md) | Angle normalization |
| [signed](signed.md) | Always-signed integer formatting |
