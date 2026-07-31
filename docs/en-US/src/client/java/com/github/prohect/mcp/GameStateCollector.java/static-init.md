# static-init (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Remarks

Executed once when the `GameStateCollector` class is loaded. Initializes the `HELD_KEY_NAMES` map with mappings from alias names to human-readable held-key names used in the `held_keys` state member:

- `"forward"` → `"forward"`
- `"back"` → `"back"`
- `"left"` → `"left"`
- `"right"` → `"right"`
- `"jump"` → `"jump"`
- `"sneak"` → `"sneak"`
- `"sprint"` → `"sprint"`
- `"attack"` → `"attack"`
- `"use"` → `"use"`
- `"drop"` → `"drop"`

This map is read by `heldKeysJson` to determine which currently-held aliases are reported in the envelope.

## See Also

| Item | Description |
|------|-------------|
| [heldKeysJson](heldKeysJson.md) | The reader of this map |
