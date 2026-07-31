package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithStringArgs;
import java.util.regex.Pattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class UnbindAlias extends BuiltinAliasWithStringArgs<UnbindAlias> {

    public UnbindAlias() {
        super("unbind");
    }

    @Override
    public UnbindAlias run(String args) {
        String line = "unbind" + Alias.divider4AliasDefinition
                + args.replaceAll(Pattern.quote(String.valueOf(divider4AliasDefinition)),
                        String.valueOf(Alias.divider4AliasDefinition)).trim();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null)
            BindAliasClient.LOGGER.warn("{}[UnbindAlias]player is null", BindAliasClient.tickPrefix());
        else
            player.networkHandler.sendChatCommand(line);
        return this;
    }
}
