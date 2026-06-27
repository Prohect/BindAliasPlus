package com.github.prohect.mixin.client;

import com.github.prohect.alias.Alias;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.CommandBlockScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class GuiMixin {

    @Inject(at = @At("RETURN"), method = "setScreen")
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        Alias.isUnderTextInputScreen.set(
            screen instanceof ChatScreen ||
                screen instanceof CommandBlockScreen ||
                screen instanceof SignEditScreen ||
                screen instanceof BookEditScreen
        );
        Alias.isUnderAnyScreen.set(screen != null);
    }
}
