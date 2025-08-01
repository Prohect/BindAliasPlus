package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyPressed;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyBoardMixin {
    @Inject(at = @At("HEAD"), method = "onKey")
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
//        BindAliasPlusClient.LOGGER.info("{}: {}", key, action);
        InputUtil.Key keyFromCode = InputUtil.Type.KEYSYM.createFromCode(key);
        if (BindAliasPlusClient.BINDING_PLUS.containsKey(keyFromCode)) {
            //switch action because 0 -> release 1 -> down 2 -> pressing, and 2 is triggered constantly
            switch (action) {
                case 0:
                    BindAliasPlusClient.keyQueue.add(new KeyPressed(keyFromCode, false));
                    break;
                case 1:
                    BindAliasPlusClient.keyQueue.add(new KeyPressed(keyFromCode, true));
                    break;
            }
        }
    }
}
