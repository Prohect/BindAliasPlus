package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import com.github.prohect.alias.UserAlias;

public class WaitAliasRecord {

    /**
     * same as tick of mc
     */
    int ticks;
    /**
     * definition of the task (via new UserAlias(definition))
     */
    final String definition;

    boolean reapplyToGameKeyMapping;

    /**
     * make sure the definition is simply the alias name when reapplyToGameKeyMapping is true
     */
    public WaitAliasRecord(int ticks, String definition, boolean reapplyToGameKeyMapping) {
        this.ticks = ticks;
        this.definition = definition;
        this.reapplyToGameKeyMapping = reapplyToGameKeyMapping;
    }

    /**
     * @return 1 if taskWaiting performed, 0 if taskWaiting still wait
     */
    public int tick() {
        if (ticks <= 0) {
            if (reapplyToGameKeyMapping) {
                // assume that the arg is simply the alias name
                if (Alias.aliasesWithArgs.get(definition) instanceof BuiltinAliasWithBooleanArgs alias && alias != null) {
                    alias.reapplyToGameKeyMapping();
                }
                if (Alias.aliasesWithArgs_notSuggested.get(definition) instanceof BuiltinAliasWithBooleanArgs alias
                        && alias != null) {
                    alias.reapplyToGameKeyMapping();
                }
                WaitAlias.tasksWaiting.remove(this);
                return 1;
            }
            new UserAlias(definition).run("");
            WaitAlias.tasksWaiting.remove(this);
            return 1;
        }
        --ticks;
        return 0;
    }
}
