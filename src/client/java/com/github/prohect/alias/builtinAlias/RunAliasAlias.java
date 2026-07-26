package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;

/**
 * Executes a registered alias by name. The argument is the alias name (optionally followed by backslash-args).
 */
public class RunAliasAlias extends BuiltinAliasWithGreedyStringArgs<RunAliasAlias> {

    public RunAliasAlias() {
        super("builtinRunAlias");
    }

    @Override
    public RunAliasAlias run(String args) {
        if (args == null || args.isBlank()) {
            BindAliasPlusClient.LOGGER.warn("{}[builtinRunAlias] No alias name provided", BindAliasPlusClient.tickPrefix());
            return this;
        }

        // Split into alias name and optional extra args
        String aliasName;
        String extraArgs;
        int splitIdx = args.indexOf(Alias.divider4AliasArgs);
        if (splitIdx != -1) {
            aliasName = args.substring(0, splitIdx).trim();
            extraArgs = args.substring(splitIdx + 1);
        } else {
            aliasName = args.trim();
            extraArgs = "";
        }

        // Try the alias registries
        Alias<?> alias = Alias.aliasesWithoutArgs.get(aliasName);
        if (alias == null) {
            alias = Alias.aliasesWithoutArgs_notSuggested.get(aliasName);
        }
        if (alias == null) {
            alias = Alias.aliasesWithArgs.get(aliasName);
        }
        if (alias == null) {
            alias = Alias.aliasesWithArgs_notSuggested.get(aliasName);
        }

        if (alias != null) {
            alias.run(extraArgs);
        } else {
            BindAliasPlusClient.LOGGER.warn("{}[builtinRunAlias] Unknown alias: {}", BindAliasPlusClient.tickPrefix(), aliasName);
        }
        return this;
    }
}
