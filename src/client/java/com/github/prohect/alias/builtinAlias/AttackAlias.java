package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class AttackAlias extends BuiltinAliasWithBooleanArgs<AttackAlias> {


    @Override
    public AttackAlias run(String args) {
        parseArgs(args);
        KeyBinding attackKey = MinecraftClient.getInstance().options.attackKey;
        attackKey.setPressed(flag);
        if (flag) KeyBinding.onKeyPressed(attackKey.boundKey);
        return this;
    }

}
