# hotbarItems method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static Map<String, String> hotbarItems(ClientPlayerEntity p)
```

## Return value

A map from hotbar slot key (`"1"`–`"9"`) to item description string. Only non-empty slots are included.

## Remarks

Iterates the player's hotbar inventory slots (indices 0-8). For each non-empty slot, formats the item description via `appendTooltipIfValuable` (including enchantment/lore annotations). The slot key is 1-based (`"1"`–`"9"`).

## See Also

| Item | Description |
|------|-------------|
| [hotbarFullJson](hotbarFullJson.md) | Formats the result as JSON |
| [hotbarDiffJson](hotbarDiffJson.md) | Produces per-slot diffs |
| [appendTooltipIfValuable](appendTooltipIfValuable.md) | Item description formatter |
