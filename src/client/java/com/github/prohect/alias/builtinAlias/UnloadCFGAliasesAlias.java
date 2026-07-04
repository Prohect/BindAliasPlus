package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import com.github.prohect.alias.UserAlias;
import java.util.ArrayList;
import java.util.List;

/**
 * UnloadCFGAliasesAlias - Remove all user aliases that were loaded from config file
 *
 * Usage: unloadCFGAliases
 *
 * This will remove all user aliases that have fromAutoload=true, which are
 * aliases created during the loadCFG() process from the config file.
 * Runtime-created aliases (via /alias command) are not affected.
 */
public class UnloadCFGAliasesAlias
    extends BuiltinAliasWithoutArgs<UnloadCFGAliasesAlias>
{

    public UnloadCFGAliasesAlias() {
        super("unloadCFGAliases");
    }

    @Override
    public UnloadCFGAliasesAlias run(String args) {
        List<String> toRemove = new ArrayList<>();

        // Find all autoloaded user aliases
        Alias.aliasesWithoutArgs.forEach((name, alias) -> {
            if (alias instanceof UserAlias userAlias) {
                if (userAlias.isFromAutoload()) {
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

        if (!BindAliasPlusClient.silentMode) {
            BindAliasPlusClient.LOGGER.info(
                "{}[unloadCFGAliases] Removed {} autoloaded alias(es)",
                BindAliasPlusClient.tickPrefix(),
                count
            );
        }

        return this;
    }
}
