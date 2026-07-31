# targetJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String targetJson(MinecraftClient mc, ClientPlayerEntity p)
```

## Return value

JSON object string: `{"type":"...","name":"...","pos":{...}}` for the entity the player is looking at, or `null` if no entity is targeted.

## Remarks

Uses the player's `HitResult` (from `mc.hitResult`). If the hit result is an `EntityHitResult`, extracts the entity type (registry key), display name, and position. Returns `null` for block hit results or when no target exists.

## See Also

| Item | Description |
|------|-------------|
| [posJson](posJson.md) | Shared position formatting |
