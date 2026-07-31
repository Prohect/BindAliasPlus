package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class PitchAlias extends BuiltinAliasWithDoubleArgs<PitchAlias> {

    public PitchAlias() {
        super("pitch");
    }

    @Override
    public PitchAlias run(String args) {
        parseArgs(args);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[pitch]Player is null", BindAliasClient.tickPrefix());
            return this;
        }
        player.setPitch((float) flag + player.getPitch());
        return this;
    }
}
