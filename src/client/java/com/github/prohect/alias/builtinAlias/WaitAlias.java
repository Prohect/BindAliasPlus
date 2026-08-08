package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;
import com.github.prohect.alias.UserAlias;

import java.util.ArrayList;
import java.util.List;

public class WaitAlias extends BuiltinAliasWithIntegerArgs<WaitAlias> {

    public WaitAlias() {
        super("wait");
    }

    public static final ArrayList<WaitAliasRecord> tasksWaiting = new ArrayList<>();

    @Deprecated
    @Override
    public WaitAlias run(String args) {
        parseArgs(args);
        if (flag < 0)
            BindAliasClient.LOGGER.error("{}Invalid arguments: ticks of waitAlias could only be positive integers.",
                    BindAliasClient.tickPrefix());
        return this;
    }

    public WaitAlias run(String args, String definition, List<UserAlias> userAliasesCallChains) {
        parseArgs(args);
        if (flag > 0)
            tasksWaiting.add(new WaitAliasRecord(flag, definition, false));
        else if (flag == 0)
            new UserAlias(definition).runInternal(userAliasesCallChains);
        else
            BindAliasClient.LOGGER.error("{}Invalid arguments:ticks not expected", BindAliasClient.tickPrefix());
        return this;
    }
}
