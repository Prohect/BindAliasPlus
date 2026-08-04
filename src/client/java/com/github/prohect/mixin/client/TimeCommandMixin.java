package com.github.prohect.mixin.client;

import net.minecraft.command.permission.PermissionCheck;
import net.minecraft.command.permission.PermissionSourcePredicate;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.TimeCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Makes the vanilla {@code /time} command family usable without cheats:
 * <ul>
 * <li>the root {@code requires(requirePermissionLevel(GAMEMASTERS_CHECK))} is replaced with an always-passing check, so the
 * integrated server accepts {@code /time query}, {@code /time set} and {@code /time add} in singleplayer worlds with cheats
 * off.</li>
 * </ul>
 * Client-side only: on a remote server the client never executes commands, so this never affects multiplayer.
 */
@Mixin(TimeCommand.class)
public class TimeCommandMixin {

    @Redirect(method = "register", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/command/CommandManager;requirePermissionLevel(Lnet/minecraft/command/permission/PermissionCheck;)Lnet/minecraft/command/permission/PermissionSourcePredicate;"))
    private static PermissionSourcePredicate<?> bindAliasAllowNoCheat(PermissionCheck permission) {
        return CommandManager.requirePermissionLevel(PermissionCheck.AlwaysPass.INSTANCE);
    }
}
