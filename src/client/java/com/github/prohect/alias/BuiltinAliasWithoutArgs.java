package com.github.prohect.alias;

import org.jetbrains.annotations.NotNull;

public abstract class BuiltinAliasWithoutArgs<T extends BuiltinAliasWithoutArgs<T>> implements AliasWithoutArgs<T> {

	@NotNull
	public final String builtinAliasName;

	protected BuiltinAliasWithoutArgs(String builtinAliasName) {
		this.builtinAliasName = builtinAliasName;
	}

	@SuppressWarnings("unchecked")
	public T putToAliasesWithoutArgs() {
		Alias.aliasesWithoutArgs.put(this.builtinAliasName, this);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T putToAliasesWithoutArgs_notSuggested() {
		Alias.aliasesWithoutArgs_notSuggested.put(this.builtinAliasName, this);
		return (T) this;
	}
}
