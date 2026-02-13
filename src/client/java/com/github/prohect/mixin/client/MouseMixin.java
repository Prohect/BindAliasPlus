package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyPressed;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.CommandBlockScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.input.MouseInput;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DuplicatedCode")
@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(at = @At("HEAD"), method = "onMouseButton")
    private void onMouseButton(
        long window,
        MouseInput input,
        int action,
        CallbackInfo ci
    ) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (window != minecraftClient.getWindow().getHandle()) return;
        InputUtil.Key key = InputUtil.Type.MOUSE.createFromCode(input.button());
        if (minecraftClient.player != null) {
            Screen sc = minecraftClient.currentScreen;
            if (
                sc instanceof ChatScreen ||
                sc instanceof CommandBlockScreen ||
                sc instanceof SignEditScreen ||
                sc instanceof BookEditScreen
            ) return;
        }
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
    @Inject(at = @At("RETURN"), method = "lockCursor")
    private void lockCursor(CallbackInfo ci) {
        Alias.aliasesWithArgs_notSuggested.forEach(
            (aliasName, aliasWithArgs) -> {
                if (
                    aliasWithArgs instanceof
                        BuiltinAliasWithBooleanArgs<
                            ?
                        > builtinAliasWithBooleanArgs
                ) if (
                    builtinAliasWithBooleanArgs.flag &&
                    !Alias.blackList4lockCursor.contains(
                        builtinAliasWithBooleanArgs
                    )
                ) builtinAliasWithBooleanArgs.run("1");
            }
        );
        Alias.aliasesWithArgs.forEach((aliasName, aliasWithArgs) -> {
            if (
                aliasWithArgs instanceof
                    BuiltinAliasWithBooleanArgs<?> builtinAliasWithBooleanArgs
            ) if (
                builtinAliasWithBooleanArgs.flag &&
                !Alias.blackList4lockCursor.contains(
                    builtinAliasWithBooleanArgs
                )
            ) builtinAliasWithBooleanArgs.run("1");
        });
    }
}
