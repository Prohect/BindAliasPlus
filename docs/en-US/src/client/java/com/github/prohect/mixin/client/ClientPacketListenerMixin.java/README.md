# ClientPacketListenerMixin

Mixin targeting `net.minecraft.client.multiplayer.ClientPacketListener`. Intercepts recipe-book-add packets to feed the `RECIPE` channel.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [onRecipeBookAdd](onRecipeBookAdd.md) | `void onRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci)` | `@Inject` at `HEAD` of `handleRecipeBookAdd` — reports newly unlocked recipe names to the `RECIPE` channel |

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | Destination channel for recipe notifications |
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/README.md) | Read side for the `listRecipes`/`applyRecipe` APIs |
