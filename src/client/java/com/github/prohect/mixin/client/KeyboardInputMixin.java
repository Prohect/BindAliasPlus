package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyBindingPlus;
import com.github.prohect.KeyPressed;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private static void tick(CallbackInfo info) {
        KeyPressed keyPressed;
        while ((keyPressed = BindAliasPlusClient.keyQueue.poll()) != null) {
            // should only be aliasWithoutArgs, so the args would be an empty string
            KeyBindingPlus keyBindingPlus;
            if ((keyBindingPlus = BindAliasPlusClient.BINDING_PLUS.get(keyPressed.key())) != null) {
                if (keyPressed.pressed()) {
                    keyBindingPlus.keyPressed().run("");
                } else {
                    keyBindingPlus.keyReleased().run("");
                }
            }
        }
    }
}
