package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import java.util.ArrayList;
import java.util.List;

/**
 * UnloadCFGVarsAlias - Remove all variables that were loaded from config file
 *
 * Usage: unloadCFGVars
 *
 * This will remove all variables that were tracked as autoloaded, which are variables created during the loadCFG() process from
 * the config file. Runtime-created variables (via /var command) are not affected.
 */
public class UnloadCFGVarsAlias extends BuiltinAliasWithoutArgs<UnloadCFGVarsAlias> {

    public UnloadCFGVarsAlias() {
        super("unloadCFGVars");
    }

    @Override
    public UnloadCFGVarsAlias run(String args) {
        List<String> toRemove = new ArrayList<>();

        // Find all autoloaded variables
        for (String varName : VarAlias.AUTOLOADED_VARIABLES) {
            toRemove.add(varName);
        }

        // Remove them
        int count = 0;
        for (String varName : toRemove) {
            VarAlias.VARIABLES.remove(varName);
            VarAlias.AUTOLOADED_VARIABLES.remove(varName);
            count++;
        }

        if (!BindAliasPlusClient.silentMode) {
            BindAliasPlusClient.LOGGER.info("{}[unloadCFGVars] Removed {} autoloaded variable(s)",
                    BindAliasPlusClient.tickPrefix(), count);
        }

        return this;
    }
}
