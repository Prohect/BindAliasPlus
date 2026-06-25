package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

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
                BindAliasPlusClient.LOGGER.warn("[Use]Invalid arguments");
                break;
        }
        KeyMapping attackKey = Minecraft.getInstance().options.keyUse;
        attackKey.setDown(flag);
        if (flag) {
            attackKey.clickCount++;
        }
        return this;
    }
}
