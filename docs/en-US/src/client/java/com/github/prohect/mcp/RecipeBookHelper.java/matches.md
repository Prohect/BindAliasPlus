# matches method (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public static boolean matches(RecipeInfo r, String query)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `r` | `RecipeInfo` | The recipe record to test |
| `query` | `String` | User query (same rules as `find`) |

## Return value

`true` if `query` matches `r`'s item ID (exact, with or without `"minecraft:"` prefix) or locale display name (case-insensitive substring).

## Remarks

Same matching rules as `find` but applied to a single `RecipeInfo` record. Used by `handleListRecipes` for the per-query filtering mode. Case-insensitive: `"TORCH"`, `"torch"`, and `"Torch"` all match equally.

## See Also

| Item | Description |
|------|-------------|
| [find](find.md) | Uses the same rules to return the first match |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | The caller for query-based filtering |
