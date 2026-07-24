package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class PitchAlias extends BuiltinAliasWithDoubleArgs<PitchAlias> {

    public PitchAlias() {
        super("pitch");
    }

    @Override
    public PitchAlias run(String args) {
        parseArgs(args);
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[pitch]Player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        player.setXRot((float) flag + player.getXRot());
        return this;
    }
}
