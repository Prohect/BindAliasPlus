package com.github.prohect.mixin.client;

import com.github.prohect.alias.Alias;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.CommandBlockEditScreen;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At("RETURN"), method = "setScreen")
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        Alias.isUnderTextInputScreen.set(
            screen instanceof ChatScreen ||
                screen instanceof CommandBlockEditScreen ||
                screen instanceof SignEditScreen ||
                screen instanceof BookEditScreen
        );
        Alias.isUnderAnyScreen.set(screen != null);
    }
}
