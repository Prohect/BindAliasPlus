package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;

public class DropAlias extends BuiltinAliasWithBooleanArgs<DropAlias> {

    /**
     * @param args if args is "1", drop the whole stack, if args is "0", drop a single one
     */
    @Override
    public DropAlias run(String args) {
        parseArgs(args);
        Minecraft that = Minecraft.getInstance();
        if (that.player == null) return this;
        if (!that.player.isSpectator() && that.player.drop(flag)) {
            that.player.swing(InteractionHand.MAIN_HAND);
        }
        return this;
    }
}
