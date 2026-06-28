package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class SneakAlias extends BuiltinAliasWithBooleanArgs<SneakAlias> {

    public SneakAlias() {
        super("builtinSneak");
        addToReapplyImmediatelyAfterReleaseAll();
    }

    @Override
    public SneakAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen.get() && flag) return this;
        KeyMapping sneakKey = Minecraft.getInstance().options.keyShift;
        sneakKey.setDown(flag);
        if (flag) sneakKey.clickCount++;
        return this;
    }
}
