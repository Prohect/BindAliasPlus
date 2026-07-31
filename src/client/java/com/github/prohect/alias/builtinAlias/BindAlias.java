package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithStringArgs;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class BindAlias extends BuiltinAliasWithStringArgs<BindAlias> {

    public BindAlias() {
        super("bind");
    }

    @Override
    public BindAlias run(String args) {
        String line =
                "bind" + Alias.divider4AliasDefinition + args.replaceAll(Pattern.quote(String.valueOf(divider4AliasDefinition)),
                        String.valueOf(Alias.divider4AliasDefinition)).trim();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[BindAlias]player is null", BindAliasClient.tickPrefix());
        } else {
            player.connection.sendCommand(line);
        }
        return this;
    }
}
