package com.github.prohect;

/**
 * only aliasWithoutArgs could be triggered by a key event because it contains
 * no args
 *
 * @param aliasNameOnKeyPressed
 *            the alias to run when key is pressed
 * @param aliasNameOnKeyReleased
 *            the alias to run when key is released
 * @param fromAutoload
 *            true if this binding was loaded from config file
 */
public record KeyBindingPlus(String aliasNameOnKeyPressed, String aliasNameOnKeyReleased, boolean fromAutoload) {
	/**
	 * Convenience constructor for runtime-created bindings (not from autoload)
	 */
	public KeyBindingPlus(String aliasNameOnKeyPressed, String aliasNameOnKeyReleased) {
		this(aliasNameOnKeyPressed, aliasNameOnKeyReleased, false);
	}
}
