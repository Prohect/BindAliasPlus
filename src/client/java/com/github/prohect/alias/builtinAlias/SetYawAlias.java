package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SetYawAlias extends BuiltinAliasWithDoubleArgs<SetYawAlias> {

    public SetYawAlias() {
        super("setYaw");
    }

    @Override
    public SetYawAlias run(String args) {
        parseArgs(args);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[setYaw]Player is null", BindAliasClient.tickPrefix());
            return this;
        }
        player.setYaw((float) flag);
        return this;
    }
}
