package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.MinecraftClient;

public class SwitchSlotAlias extends BuiltinAliasWithArgs {

    /**
     * @param args pattern: slot1 slot2, spilt by white space, 0 means the second hand, 1-9 means hotbarSlots, while 10-18 means the first row of the inventory
     */
    @Override
    public void run(String args) {
        String[] strings = args.split(" ");
        if (strings.length != 2) {
            BindAliasPlusClient.LOGGER.warn("Invalid arguments");
            return;
        }
        int[] slots = new int[2];
        try {
            slots[0] = Integer.parseInt(strings[0]);
            slots[1] = Integer.parseInt(strings[1]);

            if (slots[0] < 0 || slots[1] < 0 || slots[0] > 36 || slots[1] > 36 || slots[0] == slots[1]) {
                BindAliasPlusClient.LOGGER.warn("Invalid arguments");
                return;
            }
//            MinecraftClient.getInstance().interactionManager.clickSlot(this.handler.syncId, slotId, button, actionType, this.client.player);
        }catch (NumberFormatException e) {
            BindAliasPlusClient.LOGGER.warn("Invalid arguments");
            return;
        }
    }
}
