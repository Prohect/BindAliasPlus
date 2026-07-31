package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithStringArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SayAlias extends BuiltinAliasWithStringArgs<SayAlias> {

    public SayAlias() {
        super("say");
    }

    @Override
    public SayAlias run(String args) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null)
            return this;
        player.connection.sendChat(args);
        return this;
    }
}
