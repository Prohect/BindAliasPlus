package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

/**
 * Triggers a Minecraft screenshot by calling
 * {@link Minecraft#handleGlobalKeyPress(InputConstants.Key, boolean)} with
 * the {@code keyScreenshot} keybinding — the same codepath as a native F2
 * keypress.  Avoids depending on internal {@code Screenshot} class details
 * that may vary across branches.
 */
public class ScreenshotAlias
    extends BuiltinAliasWithBooleanArgs<ScreenshotAlias> {

    public ScreenshotAlias() {
        super("builtinScreenshot");
    }

    @Override
    public ScreenshotAlias run(String args) {
        parseArgs(args);
        if (!flag) return this;
        if (Alias.isUnderTextInputScreen()) return this;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return this;
        // Native codepath — same as pressing F2
        mc.handleGlobalKeyPress(mc.options.keyScreenshot.key, false);
        return this;
    }
}
