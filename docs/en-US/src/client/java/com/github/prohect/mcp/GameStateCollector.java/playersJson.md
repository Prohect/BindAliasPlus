# playersJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String playersJson(Minecraft mc, LocalPlayer p)
```

## Return value

JSON array string of nearby players with `name`, `pos`, `dim`, and directional info using the same format as `SoundCapture.directionOf`.

## Remarks

Iterates all players in the current level. Excludes the local player. For each remote player, includes display name, position, and directional info (yaw/pitch relative to the local player's view + distance) via `SoundCapture.directionOf`. Players in different dimensions are noted with their dimension name.

## See Also

| Item | Description |
|------|-------------|
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | The shared directional formatter |
