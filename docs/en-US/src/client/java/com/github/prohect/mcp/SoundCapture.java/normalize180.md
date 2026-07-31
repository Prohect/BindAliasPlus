# normalize180 method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
private static double normalize180(double deg)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `deg` | `double` | The angle in degrees to normalize |

## Return value

The angle wrapped to the range (-180, 180].

## Remarks

Normalizes an angle to the range (-180, 180] by first taking `deg % 360.0` and then adjusting values > 180 or ≤ -180 into the target range. Used for relative yaw/pitch angles before rounding.

## See Also

| Item | Description |
|------|-------------|
| [directionOf](directionOf.md) | The caller |
| [clamp](clamp.md) | Post-processing step |
