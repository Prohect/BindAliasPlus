# ClientPacketListenerMixin

针对 `net.minecraft.client.network.ClientPlayNetworkHandler`（Yarn：`ClientPlayNetworkHandler`；Mojang：`ClientPacketListener`）的 mixin。拦截配方书添加数据包以向 `RECIPE` channel 提供数据。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onRecipeBookAdd](onRecipeBookAdd.md) | `void onRecipeBookAdd(RecipeBookAddS2CPacket packet, CallbackInfo ci)` | `onRecipeBookAdd` 的 `HEAD` 处 `@Inject`——将新解锁的配方名上报到 `RECIPE` channel |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | 配方通知的目标 channel |
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/README.md) | `listRecipes`/`applyRecipe` API 的读取侧 |
