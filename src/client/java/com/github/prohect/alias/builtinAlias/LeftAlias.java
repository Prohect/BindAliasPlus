package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class LeftAlias extends BuiltinAliasWithBooleanArgs<LeftAlias> {

	public LeftAlias() {
		super("builtinLeft");
	}

	@Override
	public LeftAlias run(String args) {
		parseArgs(args);
		// cancle press event from text input screen
		if (Alias.isUnderTextInputScreen() && flag)
			return this;
		KeyBinding leftKey = MinecraftClient.getInstance().options.leftKey;
		leftKey.setPressed(flag);
		if (flag)
			leftKey.timesPressed++;
		return this;
	}
}
