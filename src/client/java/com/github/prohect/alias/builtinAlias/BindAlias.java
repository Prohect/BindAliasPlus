package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;

public class BindAlias extends BuiltinAliasWithGreedyStringArgs<BindAlias> {

    @Override
    public BindAlias run(String args) {
        String line =
            "bind" +
            Alias.divider4AliasDefinition +
            args
                .replaceAll(
                    Pattern.quote(String.valueOf(divider4AliasDefinition)),
                    String.valueOf(Alias.divider4AliasDefinition)
                )
                .trim();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[BindAlias]player is null");
        } else {
            player.connection.sendCommand(line);
        }
        return this;
    }
}
