package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SetYawAlias extends BuiltinAliasWithDoubleArgs<SetYawAlias> {
    @Override
    public SetYawAlias run(String args) {
        parseArgs(args);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[setYaw]Player is null");
            return this;
        }
        player.setYaw((float) flag);
        return this;
    }
}
