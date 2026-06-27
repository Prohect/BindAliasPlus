package com.github.prohect.mixin.client;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import com.github.prohect.alias.builtinAlias.WaitAlias;
import com.github.prohect.alias.builtinAlias.WaitAliasRecord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.KeyMapping.class)
public class KeyMappingMixin {

    @Inject(at = @At("RETURN"), method = "releaseAll")
    private static void releaseAll(CallbackInfo ci) {
        Alias.aliasesWithArgs_notSuggested.forEach(
            (aliasName, aliasWithArgs) -> {
                if (
                    aliasWithArgs instanceof
                        BuiltinAliasWithBooleanArgs<?> builtinAliasWithBooleanArgs
                ) if (
                    builtinAliasWithBooleanArgs.flag &&
                    !Alias.blackList4lockCursor.contains(
                        builtinAliasWithBooleanArgs
                    )
                ) WaitAlias.tasksWaiting.add(
                    new WaitAliasRecord(
                        1,
                        builtinAliasWithBooleanArgs.builtinAliasName,
                        true
                    )
                );
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
            ) WaitAlias.tasksWaiting.add(
                new WaitAliasRecord(
                    1,
                    builtinAliasWithBooleanArgs.builtinAliasName,
                    true
                )
            );
        });
    }
}
