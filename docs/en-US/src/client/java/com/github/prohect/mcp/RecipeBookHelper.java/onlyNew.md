# onlyNew method (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public static synchronized List<RecipeInfo> onlyNew(List<RecipeInfo> all)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `all` | `List<RecipeInfo>` | The full list of currently unlocked recipes |

## Return value

A list of recipes not yet reported by a previous no-query `listRecipes` call. Resets on world change (detected via `BindAliasClient.joinTick`).

## Remarks

Maintains a `Set<Integer>` of already-reported `displayId.index()` values. On each call, filters `all` to only those not in the set, then adds them. On world change (joinTick different from `baselineJoinTick`), clears the set and starts fresh. Thread-safe via `synchronized`.

## See Also

| Item | Description |
|------|-------------|
| [reset](reset.md) | Manual reset of the reported set |
| [unlocked](unlocked.md) | Source of the `all` list |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | The caller for no-query mode |
