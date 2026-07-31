# getPlayerPitch method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Returns the player's current pitch angle.

## Syntax

```java
private java.lang.Double getPlayerPitch()
```

## Remarks

**Return value:** The player's current X-rotation (pitch) as a `Double`, or null if player is not available.

**Pitch orientation:** Negative values look up, positive values look down. The vanilla game clamps pitch to the range [-90, 90].

**Error handling:** Logs `"[var] Player is null"` if unavailable.

## See Also

| Item | Description |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | Primary caller (for `"pitch"` source) |
| [getPlayerYaw](getPlayerYaw.md) | Yaw counterpart |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
