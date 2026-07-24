package com.github.prohect.mixin.client;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.AliasWithArgs;
import com.github.prohect.alias.builtinAlias.DropAlias;
import com.github.prohect.alias.builtinAlias.WaitAlias;
import com.github.prohect.util.McScreenHelper;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class)
public class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        BindAliasPlusClient.currentScreen = McScreenHelper.getCurrentScreen(Minecraft.getInstance());

        int size = WaitAlias.tasksWaiting.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                size -= WaitAlias.tasksWaiting.get(i).tick();
            }
        }

        AliasWithArgs<?> raw =
            com.github.prohect.alias.Alias.aliasesWithArgs_notSuggested.get("builtinDrop");
        if (raw instanceof DropAlias dropAlias) {
            dropAlias.tickDrop();
        }
    }
}
