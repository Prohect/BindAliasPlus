package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SetYawAlias extends BuiltinAliasWithDoubleArgs<SetYawAlias> {

    public SetYawAlias() {
        super("setYaw");
    }

    @Override
    public SetYawAlias run(String args) {
        parseArgs(args);
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[setYaw]Player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        player.setYRot((float) flag);
        return this;
    }
}
