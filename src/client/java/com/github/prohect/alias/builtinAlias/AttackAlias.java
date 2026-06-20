package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

public class AttackAlias extends BuiltinAliasWithBooleanArgs<AttackAlias> {

    @Override
    public AttackAlias run(String args) {
        parseArgs(args);
        KeyMapping attackKey = Minecraft.getInstance().options.keyAttack;
        attackKey.setDown(flag);
        if (flag) attackKey.clickCount++;
        return this;
    }
}
