package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyPressed;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.CommandBlockScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//TODO:add MouseMixin
@Mixin(Keyboard.class)
public class KeyBoardMixin {
    @Inject(at = @At("HEAD"), method = "onKey")
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
//        BindAliasPlusClient.LOGGER.info("{}: {}", key, action);
        InputUtil.Key keyFromCode = InputUtil.Type.KEYSYM.createFromCode(key);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient.player != null) {
            Screen sc = minecraftClient.currentScreen;
            if (sc instanceof ChatScreen || sc instanceof CommandBlockScreen || sc instanceof SignEditScreen || sc instanceof BookEditScreen) {
                return;
            }
        }
        if (BindAliasPlusClient.BINDING_PLUS.containsKey(keyFromCode)) {
            //switch action because 0 -> release 1 -> down 2 -> pressing, and 2 is triggered constantly
            switch (action) {
                case 0:
                    BindAliasPlusClient.KEY_QUEUE.add(new KeyPressed(keyFromCode, false));
                    break;
                case 1:
                    BindAliasPlusClient.KEY_QUEUE.add(new KeyPressed(keyFromCode, true));
                    break;
            }
        }
    }
}
