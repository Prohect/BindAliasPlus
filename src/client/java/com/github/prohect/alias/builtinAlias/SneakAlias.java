package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class SneakAlias extends BuiltinAliasWithBooleanArgs {
    @Override
    public void run(String args) {
        parseArgs(args);
        KeyBinding sneakKey = MinecraftClient.getInstance().options.sneakKey;
        sneakKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(sneakKey.boundKey);
    }
}
