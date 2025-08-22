package com.github.prohect.alias.builtinAlias;

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

    public WaitAliasRecord(int ticks, String definition) {
        this.ticks = ticks;
        this.definition = definition;
    }

    /**
     *
     * @return 1 if taskWaiting performed, 0 if taskWaiting still wait
     */
    public int tick() {
        if (ticks <= 0) {
            new UserAlias(definition).run("");
            WaitAlias.tasksWaiting.remove(this);
            return 1;
        }
        --ticks;
        return 0;
    }
}
