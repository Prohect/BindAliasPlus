package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ScreenshotRecorder;

/**
 * Triggers a Minecraft screenshot (like pressing F2). Calls {@link ScreenshotRecorder#saveScreenshot} directly.
 */
public class ScreenshotAlias extends BuiltinAliasWithBooleanArgs<ScreenshotAlias> {

    public ScreenshotAlias() {
        super("builtinScreenshot");
    }

    @Override
    public ScreenshotAlias run(String args) {
        parseArgs(args);
        if (!flag)
            return this;
        if (Alias.isUnderTextInputScreen())
            return this;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null)
            return this;
        ScreenshotRecorder.saveScreenshot(mc.runDirectory, mc.getFramebuffer(),
                msg -> mc.execute(() -> mc.inGameHud.getChatHud().addMessage(msg)));
        return this;
    }
}
