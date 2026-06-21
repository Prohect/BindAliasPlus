package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.Minecraft;

/**
 * Shuts down the game cleanly (schedules a graceful stop).
 * Useful in autoload CFG for automated test workflows:
 * define test aliases, run them, then shutdown.
 */
public class ShutdownAlias extends BuiltinAliasWithoutArgs<ShutdownAlias> {

    @Override
    public ShutdownAlias run(String args) {
        BindAliasPlusClient.LOGGER.info("[shutdown] Shutting down...");
        Minecraft.getInstance().stop();
        return this;
    }
}
