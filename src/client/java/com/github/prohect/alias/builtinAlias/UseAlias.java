package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class UseAlias extends BuiltinAliasWithArgs {
    @Override
    public void run(String args) {
        boolean flag = false;
        switch (args) {
            case "0":
                break;
            case "1":
                flag = true;
                break;
            default:
                BindAliasPlusClient.LOGGER.warn("[Use]Invalid arguments");
                break;
        }
        KeyBinding attackKey = MinecraftClient.getInstance().options.useKey;
        attackKey.setPressed(flag);
        if (flag) {
            KeyBinding.onKeyPressed(attackKey.boundKey);
        }
    }
}
