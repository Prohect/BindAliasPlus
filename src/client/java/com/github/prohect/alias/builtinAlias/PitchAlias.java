package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class PitchAlias extends BuiltinAliasWithDoubleArgs<PitchAlias> {
    @Override
    public PitchAlias run(String args) {
        parseArgs(args);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[pitch]Player is null");
            return this;
        }
        player.setPitch((float) flag + player.getPitch());
        return this;
    }
}
