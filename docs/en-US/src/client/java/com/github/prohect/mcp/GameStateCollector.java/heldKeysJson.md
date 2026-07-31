# heldKeysJson method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String heldKeysJson()
```

## Return value

JSON array string of currently held movement/action key names (e.g., `["forward","attack","sneak"]`), or `null` when no keys are held.

## Remarks

Iterates all registered `BuiltinAliasWithBooleanArgs` instances (both `aliasesWithArgs_notSuggested` and `aliasesWithArgs` maps in `Alias`). For each alias whose `flag` is `true`, looks up the human-readable name in `HELD_KEY_NAMES` (keyed by alias name like `"forward"`, `"attack"`, `"sneak"`, `"jump"`, `"sprint"`, `"drop"`, etc.) and includes it in the output array. Returns `null` (not `"[]"`) when empty.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithBooleanArgs.flag](../../alias/BuiltinAliasWithBooleanArgs.java/flag.md) | The held-state flag checked here |
| [MouseMixin.lockCursor](../../mixin/client/MouseMixin.java/lockCursor.md) | Reapplies held aliases after screen transitions — the reason `held_keys` is force-included in every envelope |
