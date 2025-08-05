package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class SlotAlias extends BuiltinAliasWithArgs {
    /**
     * @param args from 1-9
     */
    @Override
    public void run(String args) {
        try {
            int i = Integer.parseInt(args);
            if (!(1<=i && i<=9)) {
                BindAliasPlusClient.LOGGER.warn("Invalid input! Please enter a number between 1 and 9");
                return;
            }

            KeyBinding hotbarKey = MinecraftClient.getInstance().options.hotbarKeys[i - 1];
            KeyBinding.onKeyPressed(hotbarKey.boundKey);
        }catch (NumberFormatException e){
            BindAliasPlusClient.LOGGER.warn("Invalid arguments for slot alias");
        }
    }
}
