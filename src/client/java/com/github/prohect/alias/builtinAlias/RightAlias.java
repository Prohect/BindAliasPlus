package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class RightAlias extends BuiltinAliasWithBooleanArgs<RightAlias> {

    @Override
    public RightAlias run(String args) {
        parseArgs(args);
        KeyBinding rightKey = MinecraftClient.getInstance().options.rightKey;
        rightKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(rightKey.boundKey);
        return this;
    }
}
