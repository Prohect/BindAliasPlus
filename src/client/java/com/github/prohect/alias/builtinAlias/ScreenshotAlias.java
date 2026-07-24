package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;

/**
 * Triggers a Minecraft screenshot (like pressing F2). Calls
 * {@link Screenshot#grab} directly since 26.1.x lacks
 * {@code handleGlobalKeyPress}.
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
		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null)
			return this;
		Screenshot.grab(mc.gameDirectory, mc.getMainRenderTarget(),
				msg -> mc.execute(() -> mc.gui.getChat().addClientSystemMessage(msg)));
		return this;
	}
}
