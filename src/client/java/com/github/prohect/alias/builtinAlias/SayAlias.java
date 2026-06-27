package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SayAlias extends BuiltinAliasWithGreedyStringArgs<SayAlias> {

    public SayAlias() {
        super("say");
    }

    @Override
    public SayAlias run(String args) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return this;
        player.networkHandler.sendChatMessage(args);
        return this;
    }
}
