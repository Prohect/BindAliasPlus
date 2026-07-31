package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasClient;
import com.github.prohect.KeyPressed;
import com.github.prohect.alias.builtinAlias.LockAlias;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DuplicatedCode")
@Mixin(Keyboard.class)
public class KeyBoardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    private void onKey(long window, int action, KeyInput input, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (window != minecraftClient.getWindow().getHandle())
            return;
        // BindAliasClient.LOGGER.info("{}: {}", input.key(), action);
        InputUtil.Key keyFromCode = InputUtil.Type.KEYSYM.createFromCode(input.key());
        // Skip mod-bound keys whose action is currently locked
        if (LockAlias.LOCKED_PHYSICAL_KEYS.contains(keyFromCode))
            return;
        if (BindAliasClient.BINDING_PLUS.containsKey(keyFromCode)) {
            // switch action because 0 -> release 1 -> down 2 -> pressing, and 2 is
            // triggered constantly
            switch (action) {
                case 0:
                    BindAliasClient.KEY_QUEUE.add(new KeyPressed(keyFromCode, false));
                    break;
                case 1:
                    BindAliasClient.KEY_QUEUE.add(new KeyPressed(keyFromCode, true));
                    break;
            }
        }
    }
}
