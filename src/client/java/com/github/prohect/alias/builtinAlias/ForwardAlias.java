package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class ForwardAlias extends BuiltinAliasWithBooleanArgs<ForwardAlias> {

    public ForwardAlias() {
        super("builtinForward");
    }

    @Override
    public ForwardAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag) return this;
        KeyBinding forwardKey =
            MinecraftClient.getInstance().options.forwardKey;
        forwardKey.setPressed(flag);
        if (flag) forwardKey.timesPressed++;
        return this;
    }
}
