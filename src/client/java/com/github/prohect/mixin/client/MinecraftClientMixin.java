package com.github.prohect.mixin.client;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.builtinAlias.WaitAlias;
import com.github.prohect.util.McScreenHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.CommandBlockEditScreen;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class)
public class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        int size = WaitAlias.tasksWaiting.size();
        if (size > 0) {
            Minecraft minecraftClient = Minecraft.getInstance();
            Screen sc = null;
            if (minecraftClient.player != null) {
                sc = McScreenHelper.getCurrentScreen(minecraftClient);
            }
            if (sc != null) Alias.isUnderTextInputScreen.set(
                sc instanceof ChatScreen ||
                    sc instanceof CommandBlockEditScreen ||
                    sc instanceof SignEditScreen ||
                    sc instanceof BookEditScreen
            );
            for (int i = 0; i < size; i++) {
                size -= WaitAlias.tasksWaiting.get(i).tick();
            }
        }
    }
}
