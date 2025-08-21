package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;

public class ReloadCFGAlias extends BuiltinAliasWithoutArgs<ReloadCFGAlias> {
    @Override
    public ReloadCFGAlias run(String args) {
        BindAliasPlusClient.loadCFG();
        return this;
    }
}
