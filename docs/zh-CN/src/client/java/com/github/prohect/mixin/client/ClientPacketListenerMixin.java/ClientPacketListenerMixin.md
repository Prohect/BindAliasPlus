# ClientPacketListenerMixin（src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java）

## 语法

```java
@Mixin(ClientPlayNetworkHandler.class)
public class com.github.prohect.mixin.client.ClientPacketListenerMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.network.ClientPlayNetworkHandler`（Yarn：`ClientPlayNetworkHandler`；Mojang：`ClientPacketListener`）以拦截配方书添加数据包。当服务器发送 `RecipeBookAddS2CPacket` 时，此 mixin 将每个新解锁配方（`Entry#shouldShowNotification() == true` 的条目）按其结果物品的语言显示名上报到 [`GameChannels.RECIPE`](../../../mcp/GameChannels.java/RECIPE.md) channel。该 channel 是尽力而为的——处理过程中的任何异常都被静默吞掉。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | 配方解锁通知的目标 channel |
| [onRecipeBookAdd](onRecipeBookAdd.md) | 拦截数据包的 `@Inject` |
