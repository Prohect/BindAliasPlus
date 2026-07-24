package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import com.github.prohect.util.McScreenHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public class ToggleInventoryAlias extends BuiltinAliasWithoutArgs<ToggleInventoryAlias> {

    public ToggleInventoryAlias() {
        super("toggleInventory");
    }

    /**
     * Toggle the inventory screen: open if closed, close if open.
     */
    @Override
    public ToggleInventoryAlias run(String args) {
        // cancel open event from text input screen
        if (Alias.isUnderTextInputScreen())
            return this;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return this;
        if (Alias.isInContainerScreen()) {
            Alias.getCurrentScreen().onClose();
        } else if (!Alias.isUnderAnyScreen()) {
            mc.player.sendOpenInventory();
            McScreenHelper.setScreen(mc, new InventoryScreen(mc.player));
        }
        return this;
    }
}
