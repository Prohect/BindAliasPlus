package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class PickItemAlias extends BuiltinAliasWithoutArgs<PickItemAlias> {

    public PickItemAlias() {
        super("pickItem");
    }

    /**
     * Triggers vanilla pick-block behavior by firing the pickItemKey
     * keybinding, which makes the game call {@code pickBlockOrEntity()}
     * in the next polling cycle.
     */
    @Override
    public PickItemAlias run(String args) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return this;
        if (Alias.isUnderTextInputScreen()) return this;
        KeyBinding pickKey = mc.options.pickItemKey;
        pickKey.setPressed(true);
        pickKey.timesPressed++;
        return this;
    }
}
