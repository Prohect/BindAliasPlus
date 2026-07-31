# onRecipeBookAdd method (src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java)

## Syntax

```java
@Inject(method = "handleRecipeBookAdd", at = @At("HEAD"))
private void onRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `packet` | `net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket` | The recipe-book-add packet from the server |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `ClientPacketListener#handleRecipeBookAdd(ClientboundRecipeBookAddPacket)`. Iterates all entries in the packet; for each entry where `notification()` is `true`, computes the result items via `contents().resultItems(SlotDisplayContext.fromLevel(level))` and posts the display name of the first result to `GameChannels.RECIPE`. Requires `Minecraft.getInstance().level` to be non-null. The entire try-block is wrapped in a silent `catch (Exception)` — recipe-channel failures must not crash the client.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | Destination channel field |
| [ClientPacketListenerMixin](ClientPacketListenerMixin.md) | The enclosing mixin class |
