package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;
import java.util.ArrayList;

public class WaitAlias extends BuiltinAliasWithIntegerArgs<WaitAlias> {

    public WaitAlias() {
        super("wait");
    }

    public static final ArrayList<WaitAliasRecord> tasksWaiting = new ArrayList<>();

    @Deprecated
    @Override
    public WaitAlias run(String args) {
        parseArgs(args);
        if (flag > 0)
            tasksWaiting.add(new WaitAliasRecord(flag, "", false));
        else
            BindAliasPlusClient.LOGGER.error("{}Invalid arguments: ticks of waitAlias could only be positive integers.",
                    BindAliasPlusClient.tickPrefix());
        return this;
    }

    public WaitAlias run(String args, String definition) {
        parseArgs(args);
        if (flag >= 0)
            tasksWaiting.add(new WaitAliasRecord(flag, definition, false));
        else
            BindAliasPlusClient.LOGGER.error("{}Invalid arguments:ticks not expected", BindAliasPlusClient.tickPrefix());
        return this;
    }
}
