package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class LeftAlias extends BuiltinAliasWithBooleanArgs<LeftAlias> {

    public LeftAlias() {
        super("builtinLeft");
    }

    @Override
    public LeftAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag) return this;
        KeyMapping leftKey = Minecraft.getInstance().options.keyLeft;
        leftKey.setDown(flag);
        if (flag) leftKey.clickCount++;
        return this;
    }
}
