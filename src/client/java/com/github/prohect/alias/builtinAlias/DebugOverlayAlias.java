package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;

/**
 * Shows/hides the debug overlay (F3) — FPS, coordinates, entity counts, etc. Uses {@code debugHudEntryList.toggleF3Enabled()}
 * directly since the debug keys are intercepted at the GLFW level, not polled via KeyMapping.
 */
public class DebugOverlayAlias extends BuiltinAliasWithBooleanArgs<DebugOverlayAlias> {

    public DebugOverlayAlias() {
        super("builtinDebugOverlay");
    }

    @Override
    public DebugOverlayAlias run(String args) {
        parseArgs(args);
        MinecraftClient mc = MinecraftClient.getInstance();
        // Check if already in desired state before toggling
        boolean enabled = mc.debugHudEntryList.f3Enabled;
        if (flag && !enabled) {
            mc.debugHudEntryList.toggleF3Enabled();
        } else if (!flag && enabled) {
            mc.debugHudEntryList.toggleF3Enabled();
        }
        return this;
    }
}
