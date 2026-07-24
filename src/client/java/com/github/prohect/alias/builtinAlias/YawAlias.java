package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class YawAlias extends BuiltinAliasWithDoubleArgs<YawAlias> {

    public YawAlias() {
        super("yaw");
    }

    @Override
    public YawAlias run(String args) {
        parseArgs(args);
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[yaw]Player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        player.setYRot((float) (player.getYRot() + flag));
        return this;
    }
}
