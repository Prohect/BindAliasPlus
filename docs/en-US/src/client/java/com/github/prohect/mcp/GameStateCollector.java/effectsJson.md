# effectsJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static String effectsJson(Collection<MobEffectInstance> effects)
```

## Return value

JSON array string of active status effects, each with `name` (display name), `amplifier` (0-indexed), and `duration` (formatted as `MM:SS`).

## Remarks

Iterates the player's active `MobEffectInstance` collection, extracting the display name from the effect's `MobEffect.getDisplayName()` via `Component.getString()`, the amplifier level, and the remaining duration (ticks converted to `MM:SS` via `formatDuration`). Returns `"[]"` when the collection is empty or null.

## See Also

| Item | Description |
|------|-------------|
| [formatDuration](formatDuration.md) | Tick→MM:SS conversion |
