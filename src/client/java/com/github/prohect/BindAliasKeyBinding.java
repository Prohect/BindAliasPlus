package com.github.prohect;

/**
 * only aliasWithoutArgs could be triggered by a key event because it contains
 * no args
 *
 * @param aliasNameOnKeyPressed
 *            the alias to run when key is pressed
 * @param aliasNameOnKeyReleased
 *            the alias to run when key is released
 * @param fromCFG
 *            true if this binding was loaded from config file
 */
public record BindAliasKeyBinding(String aliasNameOnKeyPressed, String aliasNameOnKeyReleased, boolean fromCFG) {
	/**
	 * Convenience constructor for runtime-created bindings (not from autoload)
	 */
	public BindAliasKeyBinding(String aliasNameOnKeyPressed, String aliasNameOnKeyReleased) {
		this(aliasNameOnKeyPressed, aliasNameOnKeyReleased, false);
	}
}
