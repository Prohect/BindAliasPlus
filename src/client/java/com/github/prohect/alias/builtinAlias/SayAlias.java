package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SayAlias extends BuiltinAliasWithGreedyStringArgs<SayAlias> {

    @Override
    public SayAlias run(String args) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return this;
        player.connection.sendChat(args);
        return this;
    }
}
