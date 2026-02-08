package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import java.util.regex.Pattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class AliasAlias extends BuiltinAliasWithGreedyStringArgs<AliasAlias> {

    /**
     * <p>#command#</p>
     * <p>alias </p>
     * <p>#aliasName of this alias</p>
     * <p>switchAlias</p>
     *
     * <p>#definition of this alias</p>
     * <p>alias\"aliasName1 swapSlot\19 +use wait\1 -use swapSlot\19"</p>
     * then the args for this alias should be "aliasName1 swapSlot\19 +use wait\1 -use swapSlot\19"
     */
    @Override
    public AliasAlias run(String args) {
        String line =
            "alias" +
            Alias.divider4AliasDefinition +
            args
                .replaceAll(
                    Pattern.quote(String.valueOf(divider4AliasDefinition)),
                    String.valueOf(Alias.divider4AliasDefinition)
                )
                .trim();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) BindAliasPlusClient.LOGGER.warn(
            "[AliasAlias]player is null"
        );
        else player.networkHandler.sendChatCommand(line);
        return this;
    }
}
