package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyPressed;
import com.github.prohect.alias.builtinAlias.LockAlias;
import com.github.prohect.util.McScreenHelper;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.CommandBlockEditScreen;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DuplicatedCode")
@Mixin(KeyboardHandler.class)
public class KeyBoardMixin {

    @Inject(at = @At("HEAD"), method = "keyPress")
    private void onKey(
        long window,
        int action,
        KeyEvent event,
        CallbackInfo ci
    ) {
        Minecraft minecraftClient = Minecraft.getInstance();
        if (window != minecraftClient.getWindow().handle()) return;
        //        BindAliasPlusClient.LOGGER.info("{}: {}", event.key(), action);
        InputConstants.Key keyFromCode = InputConstants.Type.KEYSYM.getOrCreate(
            event.key()
        );
        if (minecraftClient.player != null) {
            Screen sc = McScreenHelper.getCurrentScreen(minecraftClient);
            if (
                sc instanceof ChatScreen ||
                sc instanceof CommandBlockEditScreen ||
                sc instanceof SignEditScreen ||
                sc instanceof BookEditScreen
            ) return;
        }
        // Skip mod-bound keys whose action is currently locked
        if (LockAlias.LOCKED_PHYSICAL_KEYS.contains(keyFromCode)) return;
        if (BindAliasPlusClient.BINDING_PLUS.containsKey(keyFromCode)) {
            //switch action because 0 -> release 1 -> down 2 -> pressing, and 2 is triggered constantly
            switch (action) {
                case 0:
                    BindAliasPlusClient.KEY_QUEUE.add(
                        new KeyPressed(keyFromCode, false)
                    );
                    break;
                case 1:
                    BindAliasPlusClient.KEY_QUEUE.add(
                        new KeyPressed(keyFromCode, true)
                    );
                    break;
            }
        }
    }
}
