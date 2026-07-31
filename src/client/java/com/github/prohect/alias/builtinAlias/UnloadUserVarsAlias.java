package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import java.util.ArrayList;
import java.util.List;

/**
 * UnloadUserVarsAlias - Remove all variables that were created at runtime (not from config file)
 *
 * Usage: unloadUserVars
 *
 * Removes all variables from GENERAL_VARIABLES that are NOT in CFG_VARIABLES, and all variables from CONTAINER_SLOT_VARIABLES
 * that are NOT in CFG_CONTAINER_SLOT_VARIABLES. CFG-loaded variables are not affected.
 */
public class UnloadUserVarsAlias extends BuiltinAliasWithoutArgs<UnloadUserVarsAlias> {

    public UnloadUserVarsAlias() {
        super("unloadUserVars");
    }

    @Override
    public UnloadUserVarsAlias run(String args) {
        List<String> toRemoveGeneral = new ArrayList<>();
        List<String> toRemoveContainer = new ArrayList<>();

        // Find user-defined general variables (not in CFG_VARIABLES set)
        for (String varName : VarAlias.GENERAL_VARIABLES.keySet()) {
            if (!VarAlias.CFG_VARIABLES.contains(varName)) {
                toRemoveGeneral.add(varName);
            }
        }

        // Find user-defined container slot variables (not in CFG_CONTAINER_SLOT_VARIABLES set)
        for (String varName : VarAlias.CONTAINER_SLOT_VARIABLES.keySet()) {
            if (!VarAlias.CFG_CONTAINER_SLOT_VARIABLES.contains(varName)) {
                toRemoveContainer.add(varName);
            }
        }

        // Remove them
        int generalCount = 0;
        for (String varName : toRemoveGeneral) {
            VarAlias.GENERAL_VARIABLES.remove(varName);
            generalCount++;
        }

        int containerCount = 0;
        for (String varName : toRemoveContainer) {
            VarAlias.CONTAINER_SLOT_VARIABLES.remove(varName);
            containerCount++;
        }

        if (!BindAliasClient.silentMode) {
            int total = generalCount + containerCount;
            BindAliasClient.LOGGER.info("{}[unloadUserVars] Removed {} runtime variable(s) ({} general, {} container_slot)",
                    BindAliasClient.tickPrefix(), total, generalCount, containerCount);
        }

        return this;
    }
}
