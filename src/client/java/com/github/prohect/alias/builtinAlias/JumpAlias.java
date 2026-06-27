package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class JumpAlias extends BuiltinAliasWithBooleanArgs<JumpAlias> {

    public JumpAlias() {
        super("builtinJump");
    }

    @Override
    public JumpAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen.get() && flag) return this;
        KeyMapping jumpKey = Minecraft.getInstance().options.keyJump;
        jumpKey.setDown(flag);
        if (flag) jumpKey.clickCount++;
        return this;
    }
}
