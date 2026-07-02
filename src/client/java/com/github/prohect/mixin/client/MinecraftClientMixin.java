package com.github.prohect.mixin.client;

import com.github.prohect.alias.AliasWithArgs;
import com.github.prohect.alias.builtinAlias.DropAlias;
import com.github.prohect.alias.builtinAlias.WaitAlias;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        int size = WaitAlias.tasksWaiting.size();
        for (int i = 0; i < size; i++) {
            size -= WaitAlias.tasksWaiting.get(i).tick();
        }

        // Drive continuous drop while the drop-key alias is held
        AliasWithArgs<?> raw =
            com.github.prohect.alias.Alias.aliasesWithArgs_notSuggested.get(
                "builtinDrop"
            );
        if (raw instanceof DropAlias dropAlias) {
            dropAlias.tickDrop();
        }
    }
}
