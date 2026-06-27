package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

public class DropAlias extends BuiltinAliasWithBooleanArgs<DropAlias> {

    public DropAlias() {
        super("builtinDrop");
    }

    /**
     * @param args if args is "1", drop the whole stack, if args is "0", drop a single one
     */
    @Override
    public DropAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen.get() && flag) return this;
        MinecraftClient that = MinecraftClient.getInstance();
        if (that.player == null) return this;
        if (!that.player.isSpectator() && that.player.dropSelectedItem(flag)) {
            that.player.swingHand(Hand.MAIN_HAND);
        }
        return this;
    }
}
