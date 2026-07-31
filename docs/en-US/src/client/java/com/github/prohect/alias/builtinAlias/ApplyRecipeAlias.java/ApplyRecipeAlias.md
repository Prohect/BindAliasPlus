# ApplyRecipeAlias (src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java)

Builtin alias that places an unlocked, craftable recipe into the crafting grid of the currently open recipe menu (inventory, crafting table, furnace, etc.). Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ApplyRecipeAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.ApplyRecipeAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"applyRecipe"`. Usage: `applyRecipe\<query>`.

The query can be:
- A full or bare item registry ID: `minecraft:torch` or `torch`
- A case-insensitive substring of the item's locale name: `"iron sword"`

**No crafting is performed** — this only places the recipe into the crafting grid (exactly like clicking it in the recipe book). Taking the result is a separate action, typically via `swapSlot\c1\10` to move the result slot into the hotbar.

**Requirements:**
- A recipe menu must be open (player inventory, crafting table, furnace, etc.) — the container must implement `RecipeBookMenu`
- The recipe must be unlocked in the recipe book
- The player must have the required ingredients in their inventory

**Error reporting:** Errors (no menu open, not unlocked, missing ingredients, empty query) are sent as client-side system messages to the local game chat via `chatError()`. Success is logged to the mod channel.

## See Also

| Item | Description |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | Moves the crafted result out of the grid |
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/RecipeBookHelper.md) | Looks up recipes by query |
| [ListRecipes](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | MCP tool to list unlocked recipes |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
