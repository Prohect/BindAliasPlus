package com.github.prohect.mixin.client;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.command.permission.PermissionCheck;
import net.minecraft.command.permission.PermissionSourcePredicate;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.TickCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Makes the vanilla {@code /tick} command family usable without cheats and widens its rate range:
 * <ul>
 * <li>the root {@code requires(requirePermissionLevel(ADMINS_CHECK))} is replaced with an always-passing check, so the
 * integrated server accepts the commands in singleplayer worlds with cheats off;</li>
 * <li>the {@code /tick rate} argument range is widened from {@code [1.0, 10000.0]} to {@code [0.1, 10000.0]}.</li>
 * </ul>
 * Client-side only: on a remote server the client never executes commands, so this never affects multiplayer.
 */
@Mixin(TickCommand.class)
public class TickCommandMixin {

    @Redirect(method = "register", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/command/CommandManager;requirePermissionLevel(Lnet/minecraft/command/permission/PermissionCheck;)Lnet/minecraft/command/permission/PermissionSourcePredicate;"))
    private static PermissionSourcePredicate<?> bindAliasAllowNoCheat(PermissionCheck permission) {
        return CommandManager.requirePermissionLevel(PermissionCheck.AlwaysPass.INSTANCE);
    }

    @Redirect(method = "register", at = @At(value = "INVOKE",
            target = "Lcom/mojang/brigadier/arguments/FloatArgumentType;floatArg(FF)Lcom/mojang/brigadier/arguments/FloatArgumentType;"))
    private static FloatArgumentType bindAliasTickRateRange(float min, float max) {
        return FloatArgumentType.floatArg(0.1F, 10000.0F);
    }
}
