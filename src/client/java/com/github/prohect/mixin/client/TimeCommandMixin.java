package com.github.prohect.mixin.client;

import net.minecraft.commands.Commands;
import net.minecraft.server.commands.TimeCommand;
import net.minecraft.server.permissions.PermissionCheck;
import net.minecraft.server.permissions.PermissionProviderCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Makes the vanilla {@code /time} command family usable without cheats:
 * <ul>
 * <li>the root {@code requires(hasPermission(LEVEL_GAMEMASTERS))} is replaced with an always-passing check, so the integrated
 * server accepts {@code /time query}, {@code /time set}, {@code /time add}, {@code /time pause}, {@code /time resume} and
 * {@code /time rate} in singleplayer worlds with cheats off.</li>
 * </ul>
 * Client-side only: on a remote server the client never executes commands, so this never affects multiplayer.
 */
@Mixin(TimeCommand.class)
public class TimeCommandMixin {

    @Redirect(method = "register", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/Commands;hasPermission(Lnet/minecraft/server/permissions/PermissionCheck;)Lnet/minecraft/server/permissions/PermissionProviderCheck;"))
    private static PermissionProviderCheck<?> bindAliasAllowNoCheat(PermissionCheck permission) {
        return Commands.hasPermission(PermissionCheck.AlwaysPass.INSTANCE);
    }
}
