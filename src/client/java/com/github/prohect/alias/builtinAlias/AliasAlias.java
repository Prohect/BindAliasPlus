package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithStringArgs;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class AliasAlias extends BuiltinAliasWithStringArgs<AliasAlias> {

    public AliasAlias() {
        super("alias");
    }

    /**
     * <p>
     * #command#
     * </p>
     * <p>
     * alias
     * </p>
     * <p>
     * #aliasName of this alias
     * </p>
     * <p>
     * switchAlias
     * </p>
     *
     * <p>
     * #definition of this alias
     * </p>
     * <p>
     * alias\"aliasName1 swapSlot\19 +use wait\1 -use swapSlot\19"
     * </p>
     * then the args for this alias should be "aliasName1 swapSlot\19 +use wait\1 -use swapSlot\19"
     */
    @Override
    public AliasAlias run(String args) {
        String line = "alias" + Alias.divider4AliasDefinition
                + args.replaceAll(Pattern.quote(String.valueOf(divider4AliasDefinition)),
                        String.valueOf(Alias.divider4AliasDefinition)).trim();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[AliasAlias]player is null", BindAliasClient.tickPrefix());
        } else {
            player.connection.sendCommand(line);
        }
        return this;
    }
}
