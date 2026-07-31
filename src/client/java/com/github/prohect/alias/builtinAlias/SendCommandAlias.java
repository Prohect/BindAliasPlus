package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithStringArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SendCommandAlias extends BuiltinAliasWithStringArgs<SendCommandAlias> {

    public SendCommandAlias() {
        super("sendCommand");
    }

    @Override
    public SendCommandAlias run(String args) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null)
            return this;
        player.networkHandler.sendChatCommand(args);
        return this;
    }
}
