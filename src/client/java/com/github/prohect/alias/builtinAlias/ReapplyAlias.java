package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import java.util.List;

/**
 * Manually re-assert a single held-down boolean alias.
 * <p>
 * Usage: {@code reapply\forward} — calls
 * {@link BuiltinAliasWithBooleanArgs#reapplyToGameKeyMapping()} on the
 * corresponding builtin alias if its {@code flag} is {@code true}.
 * <p>
 * Accepts user-facing action names ({@code forward}, {@code attack},
 * {@code +forward}, {@code sneak}, etc.) and resolves them to their underlying
 * {@code builtin*} alias.
 * <p>
 * Useful at the end of a UserAlias sequence to re-assert a key that may have
 * been reset by a screen transition.
 */
public class ReapplyAlias extends BuiltinAliasWithArgs<ReapplyAlias> {

	public ReapplyAlias() {
		super("reapply");
	}

	/** Supported action names for command suggestions. */
	public static final List<String> SUPPORTED_ACTIONS = List.of("attack", "use", "forward", "back", "left", "right",
			"jump", "sneak", "sprint", "drop", "openInventory", "playerList");

	@Override
	public ReapplyAlias run(String args) {
		if (args == null || args.isBlank()) {
			BindAliasPlusClient.LOGGER.warn("{}[reapply] No action name provided", BindAliasPlusClient.tickPrefix());
			return this;
		}

		// Strip + / - prefix, then derive the builtin name
		String cleanName = args;
		if (cleanName.startsWith("+") || cleanName.startsWith("-")) {
			cleanName = cleanName.substring(1);
		}
		String builtinName = "builtin" + cleanName.substring(0, 1).toUpperCase() + cleanName.substring(1);

		// Look up the builtin and reapply if held
		var alias = Alias.aliasesWithArgs.get(builtinName);
		if (alias == null) {
			alias = Alias.aliasesWithArgs_notSuggested.get(builtinName);
		}
		if (alias instanceof BuiltinAliasWithBooleanArgs<?> b && b.flag) {
			b.reapplyToGameKeyMapping();
		} else {
			BindAliasPlusClient.LOGGER.warn("{}[reapply] '{}' -> '{}' not found or not held",
					BindAliasPlusClient.tickPrefix(), args, builtinName);
		}
		return this;
	}
}
