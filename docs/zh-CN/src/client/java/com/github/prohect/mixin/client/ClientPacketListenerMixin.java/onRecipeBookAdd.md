# onRecipeBookAdd 方法（src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java）

## 语法

```java
@Inject(method = "handleRecipeBookAdd", at = @At("HEAD"))
private void onRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `packet` | `net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket` | 来自服务器的配方书添加数据包 |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入于 `ClientPacketListener#handleRecipeBookAdd(ClientboundRecipeBookAddPacket)` 的 `HEAD`。遍历数据包中的所有条目；对每个 `notification()` 为 `true` 的条目，通过 `contents().resultItems(SlotDisplayContext.fromLevel(level))` 计算结果物品，并将第一个结果的显示名发布到 `GameChannels.RECIPE`。要求 `Minecraft.getInstance().level` 非 null。整个 try 块被静默的 `catch (Exception)` 包裹 —— 配方 channel 失败绝不能导致客户端崩溃。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | 目标 channel 字段 |
| [ClientPacketListenerMixin](ClientPacketListenerMixin.md) | 所属 mixin 类 |
