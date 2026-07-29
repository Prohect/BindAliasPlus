package com.github.prohect.mixin.client;

import com.github.prohect.mcp.GameChannels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Feeds the {@link GameChannels#RECIPE} channel: every recipe unlock that pops a vanilla toast notification
 * ({@code ClientboundRecipeBookAddPacket.Entry#notification()}) is reported by its result item's locale name — the same name
 * the toast shows.
 */
@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "handleRecipeBookAdd", at = @At("HEAD"))
    private void onRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci) {
        try {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level == null)
                return;
            ContextMap context = SlotDisplayContext.fromLevel(mc.level);
            for (ClientboundRecipeBookAddPacket.Entry entry : packet.entries()) {
                if (!entry.notification())
                    continue;
                List<ItemStack> results = entry.contents().resultItems(context);
                if (!results.isEmpty())
                    GameChannels.post(GameChannels.RECIPE, results.get(0).getHoverName().getString());
            }
        } catch (Exception ignored) {
            // recipe channel is best-effort
        }
    }
}
