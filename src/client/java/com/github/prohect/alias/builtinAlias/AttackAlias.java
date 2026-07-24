package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class AttackAlias extends BuiltinAliasWithBooleanArgs<AttackAlias> {

    public AttackAlias() {
        super("builtinAttack");
    }

    @Override
    public AttackAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyBinding attackKey = MinecraftClient.getInstance().options.attackKey;
        attackKey.setPressed(flag);
        if (flag)
            attackKey.timesPressed++;
        return this;
    }
}
