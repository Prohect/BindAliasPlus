package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SendCommandAlias
    extends BuiltinAliasWithGreedyStringArgs<SendCommandAlias>
{

    @Override
    public SendCommandAlias run(String args) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return this;
        player.networkHandler.sendChatCommand(args);
        return this;
    }
}
