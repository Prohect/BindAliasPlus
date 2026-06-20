package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

public class SneakAlias extends BuiltinAliasWithBooleanArgs<SneakAlias> {

    @Override
    public SneakAlias run(String args) {
        parseArgs(args);
        KeyMapping sneakKey = Minecraft.getInstance().options.keyShift;
        sneakKey.setDown(flag);
        if (flag) sneakKey.clickCount++;
        return this;
    }
}
