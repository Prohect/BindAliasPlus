package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

public class BackAlias extends BuiltinAliasWithBooleanArgs<BackAlias> {

    @Override
    public BackAlias run(String args) {
        parseArgs(args);
        KeyMapping backKey = Minecraft.getInstance().options.keyDown;
        backKey.setDown(flag);
        if (flag) backKey.clickCount++;
        return this;
    }
}
