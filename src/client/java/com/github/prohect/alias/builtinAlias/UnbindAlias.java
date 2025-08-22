package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.regex.Pattern;

public class UnbindAlias extends BuiltinAliasWithGreedyStringArgs<UnbindAlias> {
    @Override
    public UnbindAlias run(String args) {
        String line = "unbind" + Alias.divider4AliasDefinition + args.replaceAll(Pattern.quote(String.valueOf(divider4AliasDefinition)), String.valueOf(Alias.divider4AliasDefinition)).trim();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) BindAliasPlusClient.LOGGER.warn("[UnbindAlias]player is null");
        else player.networkHandler.sendChatCommand(line);
        return this;
    }
}
