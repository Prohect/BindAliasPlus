package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SetPitchAlias extends BuiltinAliasWithDoubleArgs<SetPitchAlias> {

    public SetPitchAlias() {
        super("setPitch");
    }

    @Override
    public SetPitchAlias run(String args) {
        parseArgs(args);
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[setPitch]Player is null");
            return this;
        }
        player.setXRot((float) flag);
        return this;
    }
}
