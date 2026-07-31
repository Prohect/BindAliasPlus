# worldName method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String worldName(MinecraftClient mc)
```

## Return value

The dimension registry key path as a string (e.g., `"minecraft:overworld"`, `"minecraft:the_nether"`, `"minecraft:the_end"`), or `null` if the player or level is null.

## Remarks

Extracts the world/dimension name from the player's current level via `mc.player.level().dimension().location().toString()`. Returns `null` when not in a world.

## See Also

| Item | Description |
|------|-------------|
| [collect](collect.md) | The caller |
