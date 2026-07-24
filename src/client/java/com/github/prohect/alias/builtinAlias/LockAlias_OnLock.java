package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithArgs;

/**
 * User-facing lock-key alias.
 * <p>
 * {@code +lockKey\gameKey:attack} — locks a vanilla game key. {@code +lockKey\myAlias} — locks physical keys bound to a custom
 * UserAlias.
 * <p>
 * Command completion suggests both {@code gameKey:*} actions and UserAlias names.
 *
 * @see LockAlias
 * @see LockAlias_Unlock
 */
public class LockAlias_OnLock extends BuiltinAliasWithArgs<LockAlias_OnLock> {

    public LockAlias_OnLock() {
        super("+lockKey");
    }

    @Override
    public LockAlias_OnLock run(String actionType) {
        LockAlias.lockAction(actionType);
        return this;
    }
}
