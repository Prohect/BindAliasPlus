# ClientPacketListenerMixin (src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java)

## Syntax

```java
@Mixin(ClientPlayNetworkHandler.class)
public class com.github.prohect.mixin.client.ClientPacketListenerMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.network.ClientPlayNetworkHandler` (Yarn: `ClientPlayNetworkHandler`; Mojang: `ClientPacketListener`) to intercept recipe-book-add packets. When the server sends a `RecipeBookAddS2CPacket`, this mixin reports each newly-unlocked recipe (those with `Entry#shouldShowNotification() == true`) to the [`GameChannels.RECIPE`](../../../mcp/GameChannels.java/RECIPE.md) channel by its result item's locale display name. The channel is best-effort — any exception during processing is silently swallowed.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | Destination channel for recipe unlock notifications |
| [onRecipeBookAdd](onRecipeBookAdd.md) | The `@Inject` intercepting the packet |
