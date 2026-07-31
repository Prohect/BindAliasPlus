package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithStringArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SendCommandAlias extends BuiltinAliasWithStringArgs<SendCommandAlias> {

    public SendCommandAlias() {
        super("sendCommand");
    }

    @Override
    public SendCommandAlias run(String args) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null)
            return this;
        player.connection.sendCommand(args);
        return this;
    }
}
