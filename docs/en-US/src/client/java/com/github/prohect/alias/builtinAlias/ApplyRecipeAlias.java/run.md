# run method (src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java)

Places an unlocked craftable recipe into the crafting grid of the open recipe menu.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ApplyRecipeAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The recipe query: item registry ID (`torch`, `minecraft:torch`) or locale name substring (`"iron sword"`) |

## Remarks

**Algorithm (step by step):**

1. Validates player is not null; logs warning and returns if null.
2. Trims the query string. If empty, sends error: `"[applyRecipe] usage: applyRecipe\<recipe name or item id>"` to local chat.
3. Checks that the current container menu implements `RecipeBookMenu`. If not, sends error: `"[applyRecipe] no recipe menu open (open the inventory or a crafting station)"`.
4. Looks up the recipe via `RecipeBookHelper.find(mc, query)`. If not found (null), sends error: `"[applyRecipe] not unlocked or unknown recipe: <query>"`.
5. Checks `recipe.craftable()`. If false (missing ingredients), sends error: `"[applyRecipe] missing ingredients for: <recipe name>"`.
6. Calls `mc.gameMode.handlePlaceRecipe(menu.containerId, recipe.displayId(), false)` to place the recipe into the grid. The `false` argument means "do not take all" (single craft).
7. Logs success: `"[applyRecipe] applied <recipe name>"`.

**Error reporting:** All errors use `chatError()` which sends a `Component.literal()` system message to the player's local chat (visible on HUD and in chat channel). Success is logged only to the mod logger, not shown in chat.

## See Also

| Item | Description |
|------|-------------|
| [chatError](chatError.md) | Error-to-chat helper |
| [RecipeBookHelper.find()](../../../mcp/RecipeBookHelper.java/find.md) | Recipe lookup by query |
| [SwapSlotAlias.run()](../SwapSlotAlias.java/run.md) | Moves the crafted result |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
