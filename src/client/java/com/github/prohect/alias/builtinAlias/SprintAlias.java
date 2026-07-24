package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class SprintAlias extends BuiltinAliasWithBooleanArgs<SprintAlias> {

    public SprintAlias() {
        super("builtinSprint");
    }

    @Override
    public SprintAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyMapping sprintKey = Minecraft.getInstance().options.keySprint;
        sprintKey.setDown(flag);
        if (flag)
            sprintKey.clickCount++;
        return this;
    }
}
