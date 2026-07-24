package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

public class ToggleInventoryAlias extends BuiltinAliasWithoutArgs<ToggleInventoryAlias> {

    public ToggleInventoryAlias() {
        super("toggleinventory");
    }

    /**
     * Toggle the inventory screen: open if closed, close if open.
     */
    @Override
    public ToggleInventoryAlias run(String args) {
        // cancel open event from text input screen
        if (Alias.isUnderTextInputScreen())
            return this;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null)
            return this;
        if (Alias.isInContainerScreen()) {
            Alias.getCurrentScreen().close();
        } else if (!Alias.isUnderAnyScreen()) {
            mc.setScreen(new InventoryScreen(mc.player));
        }
        return this;
    }
}
