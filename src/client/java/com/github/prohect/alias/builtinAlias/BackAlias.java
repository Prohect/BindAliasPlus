package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class BackAlias extends BuiltinAliasWithBooleanArgs {
    @Override
    public void run(String args) {
        parseArgs(args);
        KeyBinding backKey = MinecraftClient.getInstance().options.backKey;
        backKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(backKey.boundKey);
    }
}
