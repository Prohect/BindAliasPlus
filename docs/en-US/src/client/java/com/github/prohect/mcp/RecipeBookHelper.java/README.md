# RecipeBookHelper

Read side of the client recipe book for the MCP API. Lists unlocked recipes with live craftability, resolves queries by item ID or locale name, and provides diff bookkeeping for the `listRecipes` endpoint.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

**Listing and querying:**

| Name | Signature | Description |
|------|-----------|-------------|
| [unlocked](unlocked.md) | `static List<RecipeInfo> unlocked(MinecraftClient mc)` | Lists all currently unlocked recipes with live craftability |
| [find](find.md) | `static RecipeInfo find(MinecraftClient mc, String query)` | Finds the first matching unlocked recipe by item ID or name substring |
| [matches](matches.md) | `static boolean matches(RecipeInfo r, String query)` | Tests whether a single recipe matches a query |

**Diff bookkeeping:**

| Name | Signature | Description |
|------|-----------|-------------|
| [onlyNew](onlyNew.md) | `static synchronized List<RecipeInfo> onlyNew(List<RecipeInfo> all)` | Filters to recipes not yet reported (diff mode for no-query `listRecipes`) |
| [reset](reset.md) | `static void reset()` | Clears the reported-recipe set (called on world join) |

**Formatting:**

| Name | Signature | Description |
|------|-----------|-------------|
| [recipesJson](recipesJson.md) | `static String recipesJson(List<RecipeInfo> recipes)` | Formats recipe list as JSON array |

## See Also

| Item | Description |
|------|-------------|
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | The MCP endpoint |
| [ApplyRecipeAlias](../../alias/builtinAlias/ApplyRecipeAlias.java/README.md) | The `applyRecipe` alias |
