package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import com.github.prohect.alias.UserAlias;
import java.util.ArrayList;
import java.util.List;

/**
 * UnloadUserAliasesAlias - Remove all user aliases that were created at runtime (not from config file)
 *
 * Usage: unloadUserAliases
 *
 * This removes all UserAlias instances where isFromCFG() == false AND isPredefined() == false. CFG-loaded aliases and builtin
 * predefined aliases are not affected.
 */
public class UnloadUserAliasesAlias extends BuiltinAliasWithoutArgs<UnloadUserAliasesAlias> {

    public UnloadUserAliasesAlias() {
        super("unloadUserAliases");
    }

    @Override
    public UnloadUserAliasesAlias run(String args) {
        List<String> toRemove = new ArrayList<>();

        // Find all runtime-defined user aliases (not from CFG, not predefined)
        Alias.aliasesWithoutArgs.forEach((name, alias) -> {
            if (alias instanceof UserAlias userAlias) {
                if (!userAlias.isFromCFG() && !userAlias.isPredefined()) {
                    toRemove.add(name);
                }
            }
        });

        // Remove them
        int count = 0;
        for (String name : toRemove) {
            Alias.aliasesWithoutArgs.remove(name);
            count++;
        }

        if (!BindAliasClient.silentMode) {
            BindAliasClient.LOGGER.info("{}[unloadUserAliases] Removed {} runtime alias(es)", BindAliasClient.tickPrefix(),
                    count);
        }

        return this;
    }
}
