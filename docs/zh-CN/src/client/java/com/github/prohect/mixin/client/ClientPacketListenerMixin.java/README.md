# ClientPacketListenerMixin

针对 `net.minecraft.client.multiplayer.ClientPacketListener` 的 mixin。拦截配方书添加数据包以供给 `RECIPE` channel。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onRecipeBookAdd](onRecipeBookAdd.md) | `void onRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci)` | `@Inject` 于 `handleRecipeBookAdd` 的 `HEAD` —— 向 `RECIPE` channel 上报新解锁的配方名 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | 配方通知的目标 channel |
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/README.md) | `listRecipes`/`applyRecipe` API 的读取侧 |
