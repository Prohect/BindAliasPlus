package com.github.prohect.alias;

public interface AliasWithoutArgs<
    T extends AliasWithoutArgs<T>
> extends Alias<T> {
    @SuppressWarnings({ "UnusedReturnValue", "unchecked" })
    default T putToAliasesWithoutArgs(String key) {
        Alias.aliasesWithoutArgs.put(key, this);
        return (T) this;
    }
}
