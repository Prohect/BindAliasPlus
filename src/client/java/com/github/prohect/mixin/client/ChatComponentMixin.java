package com.github.prohect.mixin.client;

import com.github.prohect.mcp.ChatCapture;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatComponentMixin {

	@Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
	private void captureMessage(Text message, CallbackInfo ci) {
		capture(message.getString());
	}

	private static void capture(String text) {
		ChatCapture.onSystemMessage(text);
	}
}
