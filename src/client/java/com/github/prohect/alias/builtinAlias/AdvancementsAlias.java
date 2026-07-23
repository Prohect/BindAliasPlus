package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

/**
 * Opens/closes the advancements screen (default: L key).
 * Uses {@code keyAdvancements} which is polled via {@code consumeClick()}
 * in {@code Gui.java} — the screen opens when the key is released.
 */
public class AdvancementsAlias
    extends BuiltinAliasWithBooleanArgs<AdvancementsAlias> {

    public AdvancementsAlias() {
        super("builtinAdvancements");
    }

    @Override
    public AdvancementsAlias run(String args) {
        parseArgs(args);
        KeyMapping key = Minecraft.getInstance().options.keyAdvancements;
        key.setDown(flag);
        if (flag) key.clickCount++;
        return this;
    }
}
