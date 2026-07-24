package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class LocalSayAlias extends BuiltinAliasWithGreedyStringArgs<LocalSayAlias> {

	public LocalSayAlias() {
		super("localSay");
	}

	@Override
	public LocalSayAlias run(String args) {
		if (MinecraftClient.getInstance().player == null)
			return this;
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal(args));
		return this;
	}
}
