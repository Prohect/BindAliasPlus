package com.github.prohect.mixin.client;

import com.github.prohect.mcp.GameChannels;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks the {@link ChatHud} message-entry point so the {@link GameChannels#CHAT} channel receives every chat line shown on the
 * HUD (server/system/player messages).
 */
@Mixin(ChatHud.class)
public class ChatComponentMixin {

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
    private void captureMessage(Text message, CallbackInfo ci) {
        capture(message.getString());
    }

    private static void capture(String text) {
        GameChannels.post(GameChannels.CHAT, text);
    }
}
