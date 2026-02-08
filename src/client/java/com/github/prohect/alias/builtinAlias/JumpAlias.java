package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class JumpAlias extends BuiltinAliasWithBooleanArgs<JumpAlias> {

    @Override
    public JumpAlias run(String args) {
        parseArgs(args);
        KeyBinding jumpKey = MinecraftClient.getInstance().options.jumpKey;
        jumpKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(jumpKey.boundKey);
        return this;
    }
}
