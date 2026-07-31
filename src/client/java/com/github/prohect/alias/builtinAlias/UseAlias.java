package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class UseAlias extends BuiltinAliasWithArgs<UseAlias> {

    public UseAlias() {
        super("builtinUse");
    }

    @Override
    public UseAlias run(String args) {
        boolean flag = false;
        switch (args) {
            case "0":
                break;
            case "1":
                flag = true;
                break;
            default:
                BindAliasClient.LOGGER.warn("{}[Use]Invalid arguments", BindAliasClient.tickPrefix());
                break;
        }
        // cancel press event from text input screen
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        KeyBinding useKey = MinecraftClient.getInstance().options.useKey;
        useKey.setPressed(flag);
        if (flag) {
            useKey.timesPressed++;
        }
        return this;
    }
}
