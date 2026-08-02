package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class UseAlias extends BuiltinAliasWithBooleanArgs<UseAlias> {

    public UseAlias() {
        super("builtinUse");
    }

    @Override
    public UseAlias run(String args) {
        parseArgs(args);
        // cancel press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyMapping useKey = Minecraft.getInstance().options.keyUse;
        useKey.setDown(flag);
        if (flag)
            useKey.clickCount++;
        return this;
    }
}
