package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class BackAlias extends BuiltinAliasWithBooleanArgs<BackAlias> {

	public BackAlias() {
		super("builtinBack");
	}

	@Override
	public BackAlias run(String args) {
		parseArgs(args);
		// cancle press event from text input screen
		if (Alias.isUnderTextInputScreen() && flag)
			return this;
		KeyBinding backKey = MinecraftClient.getInstance().options.backKey;
		backKey.setPressed(flag);
		if (flag)
			backKey.timesPressed++;
		return this;
	}
}
