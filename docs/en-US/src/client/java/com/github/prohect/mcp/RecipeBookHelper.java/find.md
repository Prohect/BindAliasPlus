# find method (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public static RecipeInfo find(MinecraftClient mc, String query)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `mc` | `MinecraftClient` | The Minecraft client instance |
| `query` | `String` | User query: exact result-item ID (`"minecraft:torch"` or bare `"torch"`) or locale name substring (`"iron sword"`) |

## Return value

The first matching `RecipeInfo`, or `null` when no unlocked recipe matches.

## Remarks

Resolution order: first tries exact registry ID match (with or without `"minecraft:"` prefix). If no match, falls back to case-insensitive substring search on the locale display name. Calls `unlocked(mc)` internally, so no caching is assumed between calls.

## See Also

| Item | Description |
|------|-------------|
| [unlocked](unlocked.md) | The underlying full listing |
| [matches](matches.md) | The match logic (same rules) |
| [ApplyRecipeAlias.run](../../alias/builtinAlias/ApplyRecipeAlias.java/run.md) | The `applyRecipe` alias that calls `find` |
