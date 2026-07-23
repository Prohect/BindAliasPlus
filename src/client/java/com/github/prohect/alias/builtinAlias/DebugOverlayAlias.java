package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;

/**
 * Shows/hides the debug overlay (F3) — FPS, coordinates, entity counts, etc.
 * Uses {@code getDebugHud().toggleDebugHud()} since the debug keys are
 * intercepted at the GLFW level, not polled via KeyMapping.
 */
public class DebugOverlayAlias
    extends BuiltinAliasWithBooleanArgs<DebugOverlayAlias> {

    public DebugOverlayAlias() {
        super("builtinDebugOverlay");
    }

    @Override
    public DebugOverlayAlias run(String args) {
        parseArgs(args);
        MinecraftClient mc = MinecraftClient.getInstance();
        boolean enabled = mc.getDebugHud().shouldShowDebugHud();
        if (flag && !enabled) {
            mc.getDebugHud().toggleDebugHud();
        } else if (!flag && enabled) {
            mc.getDebugHud().toggleDebugHud();
        }
        return this;
    }
}
