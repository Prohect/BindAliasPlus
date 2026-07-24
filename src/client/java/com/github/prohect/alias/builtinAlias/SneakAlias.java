package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class SneakAlias extends BuiltinAliasWithBooleanArgs<SneakAlias> {

    public SneakAlias() {
        super("builtinSneak");
    }

    @Override
    public SneakAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyBinding sneakKey = MinecraftClient.getInstance().options.sneakKey;
        sneakKey.setPressed(flag);
        if (flag)
            sneakKey.timesPressed++;
        return this;
    }
}
