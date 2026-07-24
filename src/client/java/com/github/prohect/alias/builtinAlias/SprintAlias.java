package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class SprintAlias extends BuiltinAliasWithBooleanArgs<SprintAlias> {

    public SprintAlias() {
        super("builtinSprint");
    }

    @Override
    public SprintAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyBinding sprintKey = MinecraftClient.getInstance().options.sprintKey;
        sprintKey.setPressed(flag);
        if (flag)
            sprintKey.timesPressed++;
        return this;
    }
}
