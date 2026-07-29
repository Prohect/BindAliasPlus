package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;

/**
 * Shows/hides the debug overlay (F3) — FPS, coordinates, entity counts, etc. Uses {@code debugEntries.setOverlayVisible(flag)}
 * directly since the debug keys are intercepted at the GLFW level, not polled via KeyMapping.
 */
public class DebugOverlayAlias extends BuiltinAliasWithBooleanArgs<DebugOverlayAlias> {

    public DebugOverlayAlias() {
        super("builtinDebugOverlay");
    }

    @Override
    public DebugOverlayAlias run(String args) {
        parseArgs(args);                          // sets this.flag
        if (Alias.isUnderTextInputScreen() && flag) return this;
        Minecraft.getInstance().debugEntries.setOverlayVisible(flag);
        return this;
    }
}
