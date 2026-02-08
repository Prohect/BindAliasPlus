package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class LeftAlias extends BuiltinAliasWithBooleanArgs<LeftAlias> {

    @Override
    public LeftAlias run(String args) {
        parseArgs(args);
        KeyBinding leftKey = MinecraftClient.getInstance().options.leftKey;
        leftKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(leftKey.boundKey);
        return this;
    }
}
