# clamp method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
private static int clamp(double deg)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `deg` | `double` | The angle in degrees to clamp |

## Return value

The angle rounded to the nearest 20° step (e.g., 15 → 20, -25 → -20, 42 → 40).

## Remarks

Rounds an angle to the nearest multiple of `DEG_STEP` (20°). Coarse on purpose — the ear is not a protractor, and coarse steps produce stable, readable directional output. Returns an `int` because the result is always a multiple of `DEG_STEP`.

## See Also

| Item | Description |
|------|-------------|
| [directionOf](directionOf.md) | The caller |
| [normalize180](normalize180.md) | Pre-processing step |
