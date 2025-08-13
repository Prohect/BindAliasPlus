package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

public class DropAlias extends BuiltinAliasWithBooleanArgs {
    /**
     * @param args if args is "1", drop the whole stack, if args is "0", drop a single one
     */
    @Override
    public void run(String args) {
        parseArgs(args);
        MinecraftClient that = MinecraftClient.getInstance();
        if (that.player == null) return;
        if (!that.player.isSpectator() && that.player.dropSelectedItem(flag)) {
            that.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
