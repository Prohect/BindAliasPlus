# ClientPacketListenerMixin

Mixin targeting `net.minecraft.client.network.ClientPlayNetworkHandler` (Yarn: `ClientPlayNetworkHandler`; Mojang: `ClientPacketListener`). Intercepts recipe-book-add packets to feed the `RECIPE` channel.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [onRecipeBookAdd](onRecipeBookAdd.md) | `void onRecipeBookAdd(RecipeBookAddS2CPacket packet, CallbackInfo ci)` | `@Inject` at `HEAD` of `onRecipeBookAdd` — reports newly unlocked recipe names to the `RECIPE` channel |

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | Destination channel for recipe notifications |
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/README.md) | Read side for the `listRecipes`/`applyRecipe` APIs |
