# onRecipeBookAdd 方法（src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java）

## 语法

```java
@Inject(method = "onRecipeBookAdd", at = @At("HEAD"))
private void onRecipeBookAdd(RecipeBookAddS2CPacket packet, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `packet` | `net.minecraft.network.packet.s2c.play.RecipeBookAddS2CPacket` | 来自服务器的配方书添加数据包（Yarn：`RecipeBookAddS2CPacket`；Mojang：`ClientboundRecipeBookAddPacket`） |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入到 `ClientPlayNetworkHandler#onRecipeBookAdd(RecipeBookAddS2CPacket)` 的 `HEAD`。遍历数据包中的所有条目；对每个 `shouldShowNotification()` 为 `true` 的条目，通过 `entry.contents().getStacks(context)`（其中 `context = SlotDisplayContexts.createParameters(mc.world)`）计算结果物品，并将第一个结果的显示名发布到 `GameChannels.RECIPE`。要求 `MinecraftClient.getInstance().world` 非 null。整个 try 块被静默的 `catch (Exception)` 包裹——配方 channel 的失败绝不能导致客户端崩溃。

（Yarn：`ClientPlayNetworkHandler`；Mojang：`ClientPacketListener`）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | 目标 channel 字段 |
| [ClientPacketListenerMixin](ClientPacketListenerMixin.md) | 外层 mixin 类 |
