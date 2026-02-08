package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class SprintAlias extends BuiltinAliasWithBooleanArgs<SprintAlias> {

    @Override
    public SprintAlias run(String args) {
        parseArgs(args);
        KeyBinding sprintKey = MinecraftClient.getInstance().options.sprintKey;
        sprintKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(sprintKey.boundKey);
        return this;
    }
}
