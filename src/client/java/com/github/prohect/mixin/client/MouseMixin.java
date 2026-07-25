package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyPressed;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import com.github.prohect.alias.builtinAlias.FreeCursorAlias;
import com.github.prohect.alias.builtinAlias.LockAlias;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DuplicatedCode")
@Mixin(Mouse.class)
public class MouseMixin {

    // ── freeCursor: skip only the OS-level grab, keep the logical grab ──

    /*
     * Vanilla gates hold-to-mine (handleBlockBreaking) and camera turning on the logical cursorLocked flag, so instead of
     * cancelling lockCursor()/unlockCursor() entirely we let them run and skip only the OS-level call (glfwSetCursorPos +
     * glfwSetInputMode(GLFW_CURSOR, ...)). The game then behaves as if grabbed while the host cursor stays free.
     */
    @Inject(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/util/InputUtil;setCursorParameters(Lnet/minecraft/client/util/Window;IDD)V"),
            method = {"lockCursor", "unlockCursor"}, cancellable = true)
    private void skipOsCursorGrab(CallbackInfo ci) {
        if (FreeCursorAlias.freeCursor) {
            ci.cancel();
        }
    }

    /*
     * Physical mouse deltas must not turn the camera while freeCursor is active: the physical cursor belongs to the host
     * machine, and view control stays with the yaw/pitch aliases. Without this, the faked logical grab would newly enable
     * camera turning when moving the mouse over the focused window.
     */
    @Inject(at = @At("HEAD"), method = "updateMouse", cancellable = true)
    private void skipCameraTurn(CallbackInfo ci) {
        if (FreeCursorAlias.freeCursor) {
            ci.cancel();
        }
    }

    // ── mouse button events ──────────────────────────────────────────────

    @Inject(at = @At("HEAD"), method = "onMouseButton")
    private void onMouseButton(long window, MouseInput input, int action, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (window != minecraftClient.getWindow().getHandle())
            return;
        InputUtil.Key key = InputUtil.Type.MOUSE.createFromCode(input.button());
        if (Alias.isUnderTextInputScreen())
            return;
        // Skip mod-bound keys whose action is currently locked
        if (LockAlias.LOCKED_PHYSICAL_KEYS.contains(key))
            return;
        if (BindAliasPlusClient.BINDING_PLUS.containsKey(key)) {
            // switch action because 0 -> release 1 -> down 2 -> pressing, and 2 is
            // triggered constantly
            switch (action) {
                case 0:
                    BindAliasPlusClient.KEY_QUEUE.add(new KeyPressed(key, false));
                    break;
                case 1:
                    BindAliasPlusClient.KEY_QUEUE.add(new KeyPressed(key, true));
                    break;
            }
        }
    }

    /*
     * When the game opens a new screen covering the 3d rendering world, it'll release all the keys from gameOptions. When the
     * game closes a screen, then it returns to the 3d rendering world which while running need lock the cursor, and inside this
     * process it also checks if a key from gameOptions is pressed via check a key's state in GLFW's memory, we need to update
     * our aliases' states to the game after that.
     */
    @Inject(at = @At("RETURN"), method = "lockCursor")
    private void lockCursor(CallbackInfo ci) {
        Alias.aliasesWithArgs_notSuggested.forEach((aliasName, aliasWithArgs) -> {
            if (aliasWithArgs instanceof BuiltinAliasWithBooleanArgs<?> builtinAliasWithBooleanArgs)
                builtinAliasWithBooleanArgs.reapplyToGameKeyMapping();
        });
        Alias.aliasesWithArgs.forEach((aliasName, aliasWithArgs) -> {
            if (aliasWithArgs instanceof BuiltinAliasWithBooleanArgs<?> builtinAliasWithBooleanArgs)
                builtinAliasWithBooleanArgs.reapplyToGameKeyMapping();
        });
    }
}
