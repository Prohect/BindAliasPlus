package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class RightAlias extends BuiltinAliasWithBooleanArgs<RightAlias> {

    public RightAlias() {
        super("builtinRight");
    }

    @Override
    public RightAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen.get() && flag) return this;
        KeyMapping rightKey = Minecraft.getInstance().options.keyRight;
        rightKey.setDown(flag);
        if (flag) rightKey.clickCount++;
        return this;
    }
}
