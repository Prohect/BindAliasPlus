package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class DropAlias extends BuiltinAliasWithBooleanArgs<DropAlias> {

    public DropAlias() {
        super("builtinDrop");
    }

    /**
     * @param args 1 to press the drop key (drops one item or full stack
     *             with sprint/sneak held), 0 to release
     */
    @Override
    public DropAlias run(String args) {
        parseArgs(args);
        // cancel press event from text input screen
        if (Alias.isUnderTextInputScreen.get() && flag) return this;
        KeyBinding dropKey = MinecraftClient.getInstance().options.dropKey;
        dropKey.setPressed(flag);
        if (flag) dropKey.timesPressed++;
        return this;
    }
}
