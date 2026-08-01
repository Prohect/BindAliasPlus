package com.github.prohect.mixin.client;

import com.github.prohect.mcp.GameChannels;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.RecipeBookAddS2CPacket;
import net.minecraft.recipe.display.SlotDisplayContexts;
import net.minecraft.util.context.ContextParameterMap;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Feeds the {@link GameChannels#UNLOCKED_RECIPE} channel: every recipe unlock that pops a vanilla toast notification
 * ({@code RecipeBookAddS2CPacket.Entry#shouldShowNotification()}) is reported by its result item's locale name — the same name
 * the toast shows.
 */
@Mixin(ClientPlayNetworkHandler.class)
public class ClientPacketListenerMixin {

    @Inject(method = "onRecipeBookAdd", at = @At("HEAD"))
    private void onRecipeBookAdd(RecipeBookAddS2CPacket packet, CallbackInfo ci) {
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.world == null)
                return;
            ContextParameterMap context = SlotDisplayContexts.createParameters(mc.world);
            for (RecipeBookAddS2CPacket.Entry entry : packet.entries()) {
                if (!entry.shouldShowNotification())
                    continue;
                List<ItemStack> results = entry.contents().getStacks(context);
                if (!results.isEmpty())
                    GameChannels.post(GameChannels.UNLOCKED_RECIPE, results.get(0).getName().getString());
            }
        } catch (Exception ignored) {
            // recipe channel is best-effort
        }
    }
}
