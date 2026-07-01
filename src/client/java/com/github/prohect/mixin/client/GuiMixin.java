package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At("RETURN"), method = "setScreen")
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        BindAliasPlusClient.currentScreen = screen;
    }
}
