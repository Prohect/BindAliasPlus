package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;

public class SendCommandAlias
    extends BuiltinAliasWithGreedyStringArgs<SendCommandAlias>
{

    @Override
    public SendCommandAlias run(String args) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return this;
        player.connection.send(new ServerboundChatCommandPacket(args));
        return this;
    }
}
