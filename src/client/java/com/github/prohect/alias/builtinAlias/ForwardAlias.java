package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class ForwardAlias extends BuiltinAliasWithBooleanArgs<ForwardAlias> {

    public ForwardAlias() {
        super("builtinForward");
    }

    @Override
    public ForwardAlias run(String args) {
        parseArgs(args);
        KeyMapping forwardKey = Minecraft.getInstance().options.keyUp;
        forwardKey.setDown(flag);
        if (flag) forwardKey.clickCount++;
        return this;
    }
}
