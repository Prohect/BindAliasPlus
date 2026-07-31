package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;

/**
 * UnloadCFGAllAlias - Remove all aliases, keybindings, and variables that were loaded from config file
 *
 * Usage: unloadCFGAll
 *
 * This is a convenience alias that calls all three unload operations: - unloadCFGAliases (removes autoloaded user aliases) -
 * unloadCFGBinds (removes autoloaded keybindings) - unloadCFGVars (removes autoloaded variables)
 *
 * Runtime-created items (via commands during gameplay) are not affected.
 */
public class UnloadCFGAllAlias extends BuiltinAliasWithoutArgs<UnloadCFGAllAlias> {

    public UnloadCFGAllAlias() {
        super("unloadCFGAll");
    }

    @Override
    public UnloadCFGAllAlias run(String args) {
        // Use silent mode temporarily to avoid spam
        boolean originalSilentMode = BindAliasClient.silentMode;
        BindAliasClient.silentMode = true;

        int totalAliases = 0;
        int totalBinds = 0;
        int totalVars = 0;

        // Unload aliases
        UnloadCFGAliasesAlias unloadAliases = new UnloadCFGAliasesAlias();
        unloadAliases.run(args);
        // Count removed aliases manually since we're in silent mode
        totalAliases = (int) com.github.prohect.alias.Alias.aliasesWithoutArgs.values().stream()
                .filter(alias -> alias instanceof com.github.prohect.alias.UserAlias ua && !ua.isFromCFG()).count();
        totalAliases = com.github.prohect.alias.Alias.aliasesWithoutArgs.values().stream()
                .filter(alias -> alias instanceof com.github.prohect.alias.UserAlias).toList().size() - totalAliases;

        // Unload bindings
        UnloadCFGBindsAlias unloadBinds = new UnloadCFGBindsAlias();
        unloadBinds.run(args);
        totalBinds = (int) BindAliasClient.BINDING_PLUS.values().stream().filter(binding -> !binding.fromCFG()).count();
        totalBinds = BindAliasClient.BINDING_PLUS.size() - totalBinds;

        // Unload variables
        UnloadCFGVarsAlias unloadVars = new UnloadCFGVarsAlias();
        totalVars = VarAlias.CFG_VARIABLES.size();
        unloadVars.run(args);

        // Restore silent mode
        BindAliasClient.silentMode = originalSilentMode;

        if (!originalSilentMode) {
            BindAliasClient.LOGGER.info("{}[unloadCFGAll] Removed {} alias(es), {} keybinding(s), {} variable(s)",
                    BindAliasClient.tickPrefix(), totalAliases, totalBinds, totalVars);
        }

        return this;
    }
}
