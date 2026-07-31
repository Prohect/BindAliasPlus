package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;

public class ReloadCFGAlias extends BuiltinAliasWithoutArgs<ReloadCFGAlias> {

    public ReloadCFGAlias() {
        super("reloadCFG");
    }

    @Override
    public ReloadCFGAlias run(String args) {
        BindAliasClient.INSTANCE.loadCFG();
        return this;
    }
}
