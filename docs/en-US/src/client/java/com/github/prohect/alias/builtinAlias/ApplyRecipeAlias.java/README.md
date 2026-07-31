# ApplyRecipeAlias

String-arg alias for placing an unlocked recipe into the crafting grid. No crafting is performed — taking the result is a separate action. Errors are reported to local chat; success is logged.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none besides inherited)_ | | |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `ApplyRecipeAlias run(String args)` | Look up recipe by query and place it in the open recipe menu |
| [chatError](chatError.md) | `static void chatError(LocalPlayer, String)` | Send an error message to the player's local chat |

## See Also

| Item | Description |
|------|-------------|
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/RecipeBookHelper.md) | Recipe lookup with craftability check |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | Move crafted result out of the grid |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Base class for string-arg aliases |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
