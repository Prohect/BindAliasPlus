# recipesJson method (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public static String recipesJson(List<RecipeInfo> recipes)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `recipes` | `List<RecipeInfo>` | The list of recipes to serialize |

## Return value

A JSON array string: `[{"name":"Torch","item":"minecraft:torch","craftable":true}, ...]`. Pre-sized `StringBuilder` for late-game recipe counts (500+ unlocked recipes → ~40 KiB).

## Remarks

Formats a list of `RecipeInfo` records as JSON. Each entry has `"name"` (escaped display name), `"item"` (escaped registry ID), and `"craftable"` (boolean). Uses `GameStateCollector.jsonEscape` for string escaping to avoid double-quote/backslash breakage. The initial `StringBuilder` capacity is estimated at `recipes.size() * 80 + 2` to avoid repeated reallocations.

## See Also

| Item | Description |
|------|-------------|
| [GameStateCollector.jsonEscape](GameStateCollector.java/jsonEscape.md) | The shared string escaper |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | The caller |
