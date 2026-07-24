package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class AttackAlias extends BuiltinAliasWithBooleanArgs<AttackAlias> {

	public AttackAlias() {
		super("builtinAttack");
	}

	@Override
	public AttackAlias run(String args) {
		parseArgs(args);
		// cancle press event from text input screen
		if (Alias.isUnderTextInputScreen() && flag)
			return this;
		KeyMapping attackKey = Minecraft.getInstance().options.keyAttack;
		attackKey.setDown(flag);
		if (flag)
			attackKey.clickCount++;
		return this;
	}
}
