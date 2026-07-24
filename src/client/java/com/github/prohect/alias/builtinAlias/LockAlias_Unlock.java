package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithArgs;

/**
 * User-facing unlock-key alias — inverse of {@link LockAlias_OnLock}.
 *
 * @see LockAlias
 * @see LockAlias_OnLock
 */
public class LockAlias_Unlock extends BuiltinAliasWithArgs<LockAlias_Unlock> {

	public LockAlias_Unlock() {
		super("-lockKey");
	}

	@Override
	public LockAlias_Unlock run(String actionType) {
		LockAlias.unlockAction(actionType);
		return this;
	}
}
