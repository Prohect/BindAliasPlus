# collect method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
public static LinkedHashMap<String, String> collect()
```

## Return value

A `LinkedHashMap` of state-member name → JSON fragment. Keys are in a defined order for consistent JSON output. Members that are not available (e.g., `world_name` when not in a world) are excluded or have `null` values.

## Remarks

Assembles the full game-state snapshot from the current `MinecraftClient.getInstance()` instance. Collected members include:

- `world_name` — the dimension/level name (e.g., `"overworld"`, `"the_nether"`)
- `pos` — player position as `{"x":...,"y":...,"z":...,"yaw":...,"pitch":...}`
- `health` — `{"current":...,"max":...}`
- `effects` — active status effects as `{"name":...,"duration":"MM:SS",...}`
- `target` — the entity the player is looking at (`{"type":"...","name":"...","pos":...}`)
- `players` — nearby players with directional info (reuses `SoundCapture.directionOf`)
- `screen` — current screen name (`"inventory"`, `"crafting"`, `null` = no screen)
- `looking_at` — the block the player is looking at
- `selected_slot` — the currently selected hotbar slot (1-9)
- `held_keys` — which movement/action keys are currently held
- `inventory` — non-hotbar inventory slots
- `armor` — armor slots (feet, legs, chest, head)
- `offhand` — offhand slot item

Container and hotbar are handled separately by `StateTracker.begin` via `containerSnapshot`/`hotbarItems` to enable slot-level diffing.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | The caller |
| [containerSnapshot](containerSnapshot.md) | Container state extraction |
| [hotbarItems](hotbarItems.md) | Hotbar slot extraction |
