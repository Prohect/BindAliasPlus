package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;

/**
 * Simulates holding/releasing the player-list key (Tab). Shows the
 * online-player overlay while held — useful for an agent to capture via
 * screenshot and identify who is online.
 */
public class PlayerListAlias extends BuiltinAliasWithBooleanArgs<PlayerListAlias> {

	public PlayerListAlias() {
		super("builtinPlayerList");
	}

	@Override
	public PlayerListAlias run(String args) {
		parseArgs(args);
		if (Alias.isUnderTextInputScreen() && flag)
			return this;
		KeyBinding key = MinecraftClient.getInstance().options.playerListKey;
		key.setPressed(flag);
		if (flag)
			key.timesPressed++;
		return this;
	}
}
