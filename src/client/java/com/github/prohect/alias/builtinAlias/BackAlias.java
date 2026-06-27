package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class BackAlias extends BuiltinAliasWithBooleanArgs<BackAlias> {

    public BackAlias() {
        super("builtinBack");
    }

    @Override
    public BackAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen.get() && flag) return this;
        KeyMapping backKey = Minecraft.getInstance().options.keyDown;
        backKey.setDown(flag);
        if (flag) backKey.clickCount++;
        return this;
    }
}
