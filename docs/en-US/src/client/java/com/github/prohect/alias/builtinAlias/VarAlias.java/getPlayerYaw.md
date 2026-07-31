# getPlayerYaw method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Returns the player's current yaw angle.

## Syntax

```java
private java.lang.Double getPlayerYaw()
```

## Remarks

**Return value:** The player's current Y-rotation (yaw) as a `Double`, or null if player is not available.

**Yaw orientation:** 0 = South, 90 = West, 180 = North, 270 = East. Values are not clamped (they wrap around).

**Error handling:** Logs `"[var] Player is null"` if unavailable.

## See Also

| Item | Description |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | Primary caller (for `"yaw"` source) |
| [getPlayerPitch](getPlayerPitch.md) | Pitch counterpart |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
