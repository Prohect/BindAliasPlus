# RecipeBookHelper (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public final class com.github.prohect.mcp.RecipeBookHelper
```

## Static Initializer

_None._

## Remarks

Read side of the client recipe book for the MCP API. Lists unlocked recipes (result-item locale name + registry id + live craftability) and resolves name/id queries for the `applyRecipe` alias and the `listRecipes` MCP tool.

Craftability mirrors the recipe book's own logic: accounts for every player inventory stack plus the crafting slots of the open `RecipeBookMenu`, then checks `RecipeDisplayEntry.canCraft(StackedItemContents)`. Recipes are deduplicated by `(itemId, displayName)`. Query resolution tries exact result-item ID first (`"minecraft:torch"` or bare `"torch"`), then case-insensitive substring of the locale name.

The nested `RecipeInfo` record holds the display name, registry item ID, craftability boolean, and `RecipeDisplayId` for each recipe.

## See Also

| Item | Description |
|------|-------------|
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | The MCP endpoint that calls this |
| [ApplyRecipeAlias](../../alias/builtinAlias/ApplyRecipeAlias.java/README.md) | The `applyRecipe` alias that calls `find` |
| [unlocked](unlocked.md) | Lists all unlocked recipes |
| [find](find.md) | Finds a recipe by id or name substring |
