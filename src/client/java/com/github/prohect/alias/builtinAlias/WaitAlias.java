package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;

import java.util.ArrayList;

public class WaitAlias extends BuiltinAliasWithIntegerArgs {
    public static final ArrayList<WaitAliasRecord> tasksWaiting = new ArrayList<>();

    @Deprecated
    @Override
    public void run(String args) {
        parseArgs(args);
        if (flag > 0) tasksWaiting.add(new WaitAliasRecord(flag, ""));
        else BindAliasPlusClient.LOGGER.error("Invalid arguments: ticks of waitAlias could only be positive integers.");
    }

    public void run(String args, String definition) {
        parseArgs(args);
        if (flag > 0) tasksWaiting.add(new WaitAliasRecord(flag, definition));
        else BindAliasPlusClient.LOGGER.error("Invalid arguments:ticks not expected");
    }
}
