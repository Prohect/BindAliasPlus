package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyPressed;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import com.github.prohect.alias.builtinAlias.LockAlias;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DuplicatedCode")
@Mixin(net.minecraft.client.MouseHandler.class)
public class MouseMixin {

    @Inject(at = @At("HEAD"), method = "onButton")
    private void onMouseButton(
        long window,
        MouseButtonInfo button,
        int action,
        CallbackInfo ci
    ) {
        Minecraft minecraftClient = Minecraft.getInstance();
        if (window != minecraftClient.getWindow().handle()) return;
        InputConstants.Key key = InputConstants.Type.MOUSE.getOrCreate(
            button.button()
        );
        if (Alias.isUnderTextInputScreen.get()) return;
        // Skip mod-bound keys whose action is currently locked
        if (LockAlias.LOCKED_PHYSICAL_KEYS.contains(key)) return;
        if (BindAliasPlusClient.BINDING_PLUS.containsKey(key)) {
            //switch action because 0 -> release 1 -> down 2 -> pressing, and 2 is triggered constantly
            switch (action) {
                case 0:
                    BindAliasPlusClient.KEY_QUEUE.add(
                        new KeyPressed(key, false)
                    );
                    break;
                case 1:
                    BindAliasPlusClient.KEY_QUEUE.add(
                        new KeyPressed(key, true)
                    );
                    break;
            }
        }
    }

    /*
    When the game opens a new screen covering the 3d rendering world, it'll release all the keys from gameOptions.
    When the game closes a screen, then it returns to the 3d rendering world which while running need lock the cursor,
    and inside this process it also checks if a key from gameOptions is pressed via check a key's state in GLFW's memory,
    we need to update our aliases' states to the game after that.
     */
    @Inject(at = @At("RETURN"), method = "grabMouse")
    private void lockCursor(CallbackInfo ci) {
        Alias.aliasesWithArgs_notSuggested.forEach(
            (aliasName, aliasWithArgs) -> {
                if (
                    aliasWithArgs instanceof
                        BuiltinAliasWithBooleanArgs<?> builtinAliasWithBooleanArgs
                ) if (
                    !Alias.blackList4lockCursor.contains(
                        builtinAliasWithBooleanArgs
                    )
                ) builtinAliasWithBooleanArgs.reapplyToGameKeyMapping();
            }
        );
        Alias.aliasesWithArgs.forEach((aliasName, aliasWithArgs) -> {
            if (
                aliasWithArgs instanceof
                    BuiltinAliasWithBooleanArgs<?> builtinAliasWithBooleanArgs
            ) if (
                !Alias.blackList4lockCursor.contains(
                    builtinAliasWithBooleanArgs
                )
            ) builtinAliasWithBooleanArgs.reapplyToGameKeyMapping();
        });
    }
}
