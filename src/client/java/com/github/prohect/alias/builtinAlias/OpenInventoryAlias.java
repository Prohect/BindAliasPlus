package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

public class OpenInventoryAlias extends BuiltinAliasWithBooleanArgs<OpenInventoryAlias> {

	public OpenInventoryAlias() {
		super("builtinOpenInventory");
	}

	/**
	 * @param args
	 *            1 to open inventory (both client screen and server packet), 0 to
	 *            close the inventory screen
	 */
	@Override
	public OpenInventoryAlias run(String args) {
		parseArgs(args);
		// cancel open event from text input screen
		if (Alias.isUnderTextInputScreen() && flag)
			return this;
		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc.player == null)
			return this;
		if (flag) {
			if (!Alias.isUnderAnyScreen()) {
				mc.setScreen(new InventoryScreen(mc.player));
			}
		} else {
			if (Alias.isInContainerScreen())
				Alias.getCurrentScreen().close();
		}
		return this;
	}
}
