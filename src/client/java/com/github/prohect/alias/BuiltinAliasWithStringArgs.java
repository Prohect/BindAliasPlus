package com.github.prohect.alias;

/**
 * while normal aliases' definition is divided by Alias.divider4AliasDefinition, BuiltinAliasWithGreedyStringArgs use its own
 * one to divide aliases' definition to make its args compatible to define aliases inside
 */
public abstract class BuiltinAliasWithStringArgs<T extends BuiltinAliasWithStringArgs<T>> extends BuiltinAliasWithArgs<T> {

    protected BuiltinAliasWithStringArgs(String builtinAliasName) {
        super(builtinAliasName);
    }

    public static final char divider4AliasDefinition = ';';
}
