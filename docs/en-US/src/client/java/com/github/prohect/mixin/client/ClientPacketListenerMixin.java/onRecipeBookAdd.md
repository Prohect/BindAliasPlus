# onRecipeBookAdd method (src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java)

## Syntax

```java
@Inject(method = "onRecipeBookAdd", at = @At("HEAD"))
private void onRecipeBookAdd(RecipeBookAddS2CPacket packet, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `packet` | `net.minecraft.network.packet.s2c.play.RecipeBookAddS2CPacket` | The recipe-book-add packet from the server (Yarn: `RecipeBookAddS2CPacket`; Mojang: `ClientboundRecipeBookAddPacket`) |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `ClientPlayNetworkHandler#onRecipeBookAdd(RecipeBookAddS2CPacket)`. Iterates all entries in the packet; for each entry where `shouldShowNotification()` is `true`, computes the result items via `entry.contents().getStacks(context)` (where `context = SlotDisplayContexts.createParameters(mc.world)`) and posts the display name of the first result to `GameChannels.RECIPE`. Requires `MinecraftClient.getInstance().world` to be non-null. The entire try-block is wrapped in a silent `catch (Exception)` — recipe-channel failures must not crash the client.

(Yarn: `ClientPlayNetworkHandler`; Mojang: `ClientPacketListener`)

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.RECIPE](../../../mcp/GameChannels.java/RECIPE.md) | Destination channel field |
| [ClientPacketListenerMixin](ClientPacketListenerMixin.md) | The enclosing mixin class |
