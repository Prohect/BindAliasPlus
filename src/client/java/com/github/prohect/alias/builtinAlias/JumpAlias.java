package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class JumpAlias extends BuiltinAliasWithBooleanArgs<JumpAlias> {

    public JumpAlias() {
        super("builtinJump");
    }

    @Override
    public JumpAlias run(String args) {
        parseArgs(args);
        // cancle press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyBinding jumpKey = MinecraftClient.getInstance().options.jumpKey;
        jumpKey.setPressed(flag);
        if (flag)
            jumpKey.timesPressed++;
        return this;
    }
}
