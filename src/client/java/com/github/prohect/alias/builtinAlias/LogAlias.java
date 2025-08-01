package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;

public class LogAlias extends BuiltinAliasWithArgs {

    @Override
    public void run(String args) {
        BindAliasPlusClient.LOGGER.info(args);
    }
}
