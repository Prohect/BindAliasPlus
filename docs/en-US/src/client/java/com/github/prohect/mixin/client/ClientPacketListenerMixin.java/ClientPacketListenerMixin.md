# ClientPacketListenerMixin (src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java)

## Syntax

```java
@Mixin(ClientPacketListener.class)
public class com.github.prohect.mixin.client.ClientPacketListenerMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.multiplayer.ClientPacketListener` to intercept recipe-book-add packets. When the server sends a `ClientboundRecipeBookAddPacket`, this mixin reports each newly-unlocked recipe (those with `Entry#notification() == true`) to the [`GameChannels.RECIPE`](../../../mcp/GameChannels.java/RECIPE.md) channel by its result item's locale display name. The channel is best-effort — any exception during processing is silently swallowed.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | Destination channel for recipe unlock notifications |
| [onRecipeBookAdd](onRecipeBookAdd.md) | The `@Inject` intercepting the packet |
