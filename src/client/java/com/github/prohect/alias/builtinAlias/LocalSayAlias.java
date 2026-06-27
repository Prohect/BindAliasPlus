package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class LocalSayAlias
    extends BuiltinAliasWithGreedyStringArgs<LocalSayAlias>
{

    public LocalSayAlias() {
        super("localSay");
    }

    @Override
    public LocalSayAlias run(String args) {
        if (Minecraft.getInstance().player == null) return this;
        Minecraft.getInstance()
            .gui.getChat()
            .addClientSystemMessage(Component.literal(args));
        return this;
    }
}
