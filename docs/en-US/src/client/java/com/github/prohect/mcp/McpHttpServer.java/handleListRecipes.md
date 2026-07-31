# handleListRecipes method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleListRecipes(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; reads optional `query` parameter and returns recipe list JSON |

## Remarks

`POST /listRecipes[?query=<query>]` handler. On the main thread:

1. Calls `RecipeBookHelper.unlocked(mc)` to get all currently unlocked recipes with live craftability.
2. If `query` parameter is present:
   - Filters recipes matching the query via `RecipeBookHelper.matches()`. Returns each matching recipe as a list entry.
   - The `recipe_errors` member in the response reports query results where no recipe matched.
3. If no `query` parameter:
   - Calls `RecipeBookHelper.onlyNew()` to filter to recipes not yet reported by a previous no-query `listRecipes` call (diff mode). Resets on world change.
4. Formats results via `RecipeBookHelper::recipesJson` and includes them in the JSON envelope under `"recipes"`. Returns the standard state diff envelope.

## See Also

| Item | Description |
|------|-------------|
| [RecipeBookHelper.unlocked](RecipeBookHelper.java/unlocked.md) | Gets all unlocked recipes |
| [RecipeBookHelper.matches](RecipeBookHelper.java/matches.md) | Query matching logic |
| [RecipeBookHelper.onlyNew](RecipeBookHelper.java/onlyNew.md) | Diff filter for no-query mode |
| [RecipeBookHelper.recipesJson](RecipeBookHelper.java/recipesJson.md) | JSON formatting |
