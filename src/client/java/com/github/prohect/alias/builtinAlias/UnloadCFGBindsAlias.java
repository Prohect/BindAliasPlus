package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.util.InputUtil;

/**
 * UnloadCFGBindsAlias - Remove all keybindings that were loaded from config file
 *
 * Usage: unloadCFGBinds
 *
 * This will remove all keybindings that have fromAutoload=true, which are bindings created during the loadCFG() process from
 * the config file. Runtime-created bindings (via /bind or /bindByAliasName commands) are not affected.
 *
 * Also cleans up associated aliases from aliasesWithoutArgs_fromBindCommand.
 */
public class UnloadCFGBindsAlias extends BuiltinAliasWithoutArgs<UnloadCFGBindsAlias> {

    public UnloadCFGBindsAlias() {
        super("unloadCFGBinds");
    }

    @Override
    public UnloadCFGBindsAlias run(String args) {
        List<InputUtil.Key> toRemove = new ArrayList<>();
        List<String> aliasesToRemove = new ArrayList<>();

        // Find all autoloaded bindings
        BindAliasPlusClient.BINDING_PLUS.forEach((key, binding) -> {
            if (binding.fromCFG()) {
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
        for (InputUtil.Key key : toRemove) {
            BindAliasPlusClient.BINDING_PLUS.remove(key);
            count++;
        }

        // Remove associated aliases from bind command storage
        for (String aliasName : aliasesToRemove) {
            Alias.aliasesWithoutArgs_fromBindCommand.remove(aliasName);
        }

        if (!BindAliasPlusClient.silentMode) {
            BindAliasPlusClient.LOGGER.info("{}[unloadCFGBinds] Removed {} autoloaded keybinding(s)",
                    BindAliasPlusClient.tickPrefix(), count);
        }

        return this;
    }
}
