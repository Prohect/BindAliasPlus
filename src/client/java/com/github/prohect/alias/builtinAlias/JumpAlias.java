package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

public class JumpAlias extends BuiltinAliasWithBooleanArgs<JumpAlias> {

    @Override
    public JumpAlias run(String args) {
        parseArgs(args);
        KeyMapping jumpKey = Minecraft.getInstance().options.keyJump;
        jumpKey.setDown(flag);
        if (flag) jumpKey.clickCount++;
        return this;
    }
}
