package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class YawAlias extends BuiltinAliasWithDoubleArgs<YawAlias> {

    public YawAlias() {
        super("yaw");
    }

    @Override
    public YawAlias run(String args) {
        parseArgs(args);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[yaw]Player is null", BindAliasClient.tickPrefix());
            return this;
        }
        player.setYaw((float) (player.getYaw() + flag));
        return this;
    }
}
