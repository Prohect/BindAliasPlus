package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

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
        KeyBinding useKey = MinecraftClient.getInstance().options.useKey;
        useKey.setPressed(flag);
        if (flag)
            useKey.timesPressed++;
        return this;
    }
}
