package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class ForwardAlias extends BuiltinAliasWithBooleanArgs<ForwardAlias> {
    @Override
    public ForwardAlias run(String args) {
        parseArgs(args);
        KeyBinding forwardKey = MinecraftClient.getInstance().options.forwardKey;
        forwardKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(forwardKey.boundKey);
        return this;
    }
}
