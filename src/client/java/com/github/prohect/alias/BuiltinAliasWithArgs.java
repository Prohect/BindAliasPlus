package com.github.prohect.alias;

import org.jetbrains.annotations.NotNull;

public abstract class BuiltinAliasWithArgs<T extends BuiltinAliasWithArgs<T>> implements
    AliasWithArgs<T> {

    @NotNull
    public final String builtinAliasName;

    protected BuiltinAliasWithArgs(String builtinAliasName) {
        this.builtinAliasName = builtinAliasName;
    }

    @SuppressWarnings("unchecked")
    public T putToAliasesWithArgs() {
        Alias.aliasesWithArgs.put(this.builtinAliasName, this);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T putToAliasesWithArgs_notSuggested() {
        Alias.aliasesWithArgs_notSuggested.put(this.builtinAliasName, this);
        return (T) this;
    }
}
