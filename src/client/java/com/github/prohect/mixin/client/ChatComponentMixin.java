package com.github.prohect.mixin.client;

import com.github.prohect.mcp.GameChannels;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.multiplayer.chat.GuiMessageTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks the three public {@link ChatComponent} message-entry points so the {@link GameChannels#CHAT} channel receives every
 * chat line shown on the HUD (server/system/player messages).
 */
@Mixin(ChatComponent.class)
public class ChatComponentMixin {

    @Inject(method = "addClientSystemMessage(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
    private void onAddClientSystemMessage(Component message, CallbackInfo ci) {
        capture(message.getString());
    }

    @Inject(method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
    private void onAddServerSystemMessage(Component message, CallbackInfo ci) {
        capture(message.getString());
    }

    @Inject(method = "addPlayerMessage" + "(Lnet/minecraft/network/chat/Component;"
            + "Lnet/minecraft/network/chat/MessageSignature;" + "Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;" + ")V",
            at = @At("HEAD"))
    private void onAddPlayerMessage(Component message, MessageSignature signature, GuiMessageTag tag, CallbackInfo ci) {
        capture(message.getString());
    }

    private static void capture(String text) {
        GameChannels.post(GameChannels.CHAT, text);
    }
}
