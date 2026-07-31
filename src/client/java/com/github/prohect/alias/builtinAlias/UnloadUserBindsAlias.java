package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import com.mojang.blaze3d.platform.InputConstants;
import java.util.ArrayList;
import java.util.List;

/**
 * UnloadUserBindsAlias - Remove all keybindings that were created at runtime (not from config file)
 *
 * Usage: unloadUserBinds
 *
 * Removes all bindings where fromCFG() == false (runtime-created via /bind or /bindByAliasName commands). CFG-loaded bindings
 * are not affected.
 *
 * Also cleans up associated aliases from aliasesWithoutArgs_fromBindCommand.
 */
public class UnloadUserBindsAlias extends BuiltinAliasWithoutArgs<UnloadUserBindsAlias> {

    public UnloadUserBindsAlias() {
        super("unloadUserBinds");
    }

    @Override
    public UnloadUserBindsAlias run(String args) {
        List<InputConstants.Key> toRemove = new ArrayList<>();
        List<String> aliasesToRemove = new ArrayList<>();

        // Find all runtime-defined bindings (not from CFG)
        BindAliasClient.BINDING_PLUS.forEach((key, binding) -> {
            if (!binding.fromCFG()) {
                toRemove.add(key);
                // Track associated aliases for cleanup
                if (!binding.aliasNameOnKeyPressed().isEmpty()) {
                    aliasesToRemove.add(binding.aliasNameOnKeyPressed());
                }
                if (!binding.aliasNameOnKeyReleased().isEmpty()) {
                    aliasesToRemove.add(binding.aliasNameOnKeyReleased());
                }
            }
        });

        // Remove bindings
        int count = 0;
        for (InputConstants.Key key : toRemove) {
            BindAliasClient.BINDING_PLUS.remove(key);
            count++;
        }

        // Remove associated aliases from bind command storage
        for (String aliasName : aliasesToRemove) {
            Alias.aliasesWithoutArgs_fromBindCommand.remove(aliasName);
        }

        if (!BindAliasClient.silentMode) {
            BindAliasClient.LOGGER.info("[unloadUserBinds] Removed {} runtime keybinding(s)", count);
        }

        return this;
    }
}
