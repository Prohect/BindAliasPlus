# unlocked method (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public static List<RecipeInfo> unlocked(Minecraft mc)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `mc` | `Minecraft` | The Minecraft client instance |

## Return value

A list of `RecipeInfo` records for all currently unlocked recipes, deduplicated by `(itemId, displayName)`. Returns an empty list when no player or level is active.

## Remarks

Enumerates all `RecipeCollection` entries in the player's recipe book. For each `RecipeDisplayEntry`, computes the result items (via `SlotDisplayContext.fromLevel`) and builds a `RecipeInfo` with the first result's display name, registry ID, craftability (via `entry.canCraft(stacked)`), and display ID. Craftability accounts for the player's entire inventory plus any crafting slots in an open `RecipeBookMenu`. Works with or without an open screen.

## See Also

| Item | Description |
|------|-------------|
| [find](find.md) | Search wrapper around `unlocked` |
| [onlyNew](onlyNew.md) | Diff filter for no-query `listRecipes` |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | The MCP endpoint |
