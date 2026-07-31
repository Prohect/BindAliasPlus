# posJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String posJson(LocalPlayer p)
```

## Return value

JSON object string: `{"x":...,"y":...,"z":...,"yaw":...,"pitch":...}` with coordinates formatted to 1 decimal place and angles to 2 decimal places.

## Remarks

Formats the player's position (`getX()`, `getY()`, `getZ()`) and rotation (`getYRot()`, `getXRot()`) as a JSON object. Uses `fmt1` for coordinates and `fmt2` for angles. Returns `"null"` if the player is null.

## See Also

| Item | Description |
|------|-------------|
| [fmt1](fmt1.md) | Coordinate formatting (1 dp) |
| [fmt2](fmt2.md) | Angle formatting (2 dp) |
