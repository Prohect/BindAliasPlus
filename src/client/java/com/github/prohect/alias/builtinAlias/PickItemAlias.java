package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class PickItemAlias extends BuiltinAliasWithoutArgs<PickItemAlias> {

    public PickItemAlias() {
        super("pickItem");
    }

    /**
     * Triggers vanilla pick-block behavior by firing the keyPickItem keybinding, which makes the game call
     * {@code pickBlockOrEntity()} in the next polling cycle.
     */
    @Override
    public PickItemAlias run(String args) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return this;
        if (Alias.isUnderTextInputScreen())
            return this;
        KeyMapping pickKey = mc.options.keyPickItem;
        pickKey.setDown(true);
        pickKey.clickCount++;
        return this;
    }
}
