package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;

public class SayAlias extends BuiltinAliasWithGreedyStringArgs<SayAlias> {

    @Override
    public SayAlias run(String args) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return this;
        player.connection.send(new ServerboundChatCommandPacket(args));
        return this;
    }
}
