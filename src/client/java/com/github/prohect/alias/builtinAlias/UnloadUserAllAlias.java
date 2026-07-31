package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;

/**
 * UnloadUserAllAlias - Remove all aliases, keybindings, and variables that were created at runtime
 *
 * Usage: unloadUserAll
 *
 * Convenience alias that calls all three unload operations in silent mode, then logs a summary: - unloadUserAliases (removes
 * runtime-defined user aliases) - unloadUserBinds (removes runtime-defined keybindings) - unloadUserVars (removes
 * runtime-defined variables)
 *
 * CFG-loaded and builtin items are not affected.
 */
public class UnloadUserAllAlias extends BuiltinAliasWithoutArgs<UnloadUserAllAlias> {

    public UnloadUserAllAlias() {
        super("unloadUserAll");
    }

    @Override
    public UnloadUserAllAlias run(String args) {
        // Use silent mode temporarily to avoid spam
        boolean originalSilentMode = BindAliasClient.silentMode;
        BindAliasClient.silentMode = true;

        int totalAliases = 0;
        int totalBinds = 0;
        int totalVars = 0;

        // Unload runtime aliases
        UnloadUserAliasesAlias unloadAliases = new UnloadUserAliasesAlias();
        unloadAliases.run(args);
        // Count removed aliases: those that are neither CFG nor predefined
        totalAliases = (int) com.github.prohect.alias.Alias.aliasesWithoutArgs.values().stream().filter(
                alias -> alias instanceof com.github.prohect.alias.UserAlias ua && (ua.isFromCFG() || ua.isPredefined()))
                .count();
        totalAliases = com.github.prohect.alias.Alias.aliasesWithoutArgs.values().stream()
                .filter(alias -> alias instanceof com.github.prohect.alias.UserAlias).toList().size() - totalAliases;

        // Unload runtime bindings
        UnloadUserBindsAlias unloadBinds = new UnloadUserBindsAlias();
        unloadBinds.run(args);
        totalBinds = (int) BindAliasClient.BINDING_PLUS.values().stream()
                .filter(com.github.prohect.BindAliasKeyBinding::fromCFG).count();
        totalBinds = BindAliasClient.BINDING_PLUS.size() - totalBinds;

        // Unload runtime variables
        int generalUserVars = (int) VarAlias.GENERAL_VARIABLES.keySet().stream()
                .filter(name -> !VarAlias.CFG_VARIABLES.contains(name)).count();
        int containerUserVars = (int) VarAlias.CONTAINER_SLOT_VARIABLES.keySet().stream()
                .filter(name -> !VarAlias.CFG_CONTAINER_SLOT_VARIABLES.contains(name)).count();
        totalVars = generalUserVars + containerUserVars;
        UnloadUserVarsAlias unloadVars = new UnloadUserVarsAlias();
        unloadVars.run(args);

        // Restore silent mode
        BindAliasClient.silentMode = originalSilentMode;

        if (!originalSilentMode) {
            BindAliasClient.LOGGER.info("{}[unloadUserAll] Removed {} alias(es), {} keybinding(s), {} variable(s)",
                    BindAliasClient.tickPrefix(), totalAliases, totalBinds, totalVars);
        }

        return this;
    }
}
