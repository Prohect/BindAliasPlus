package com.github.prohect.alias;

import java.util.ArrayList;

/**
 * a userAlias could not have args
 */
public final class UserAlias implements AliasWithoutArgs {
    ArrayList<AliasWithArgsRecord> aliases;
    final String args;

    public UserAlias(String args) {
        this.args = args;
    }

    private void decodeArgs2Alias(String args) {
        aliases = new ArrayList<>();
        String[] strings = args.split("\\|");
        for (String string : strings) {
            String[] splits = string.split("\\\\");
            int count = splits.length;
            for (String split : splits) if (split.isBlank()) --count;
            switch (count) {
                case 0:
                    break;
                case 1:
                    String aliasName = "";
                    for (String split : splits) {
                        if (!split.isBlank()) {
                            aliasName = split.trim();
                            break;
                        }
                    }
                    AliasWithoutArgs aliasWithoutArgs = Aliases.aliasesWithoutArgs.get(aliasName);
                    if (aliasWithoutArgs != null)
                        aliases.add(new AliasWithArgsRecord(aliasWithoutArgs, ""));
                    break;
                case 2:
                    String aliasName2 = "";
                    String args2 = "";
                    boolean flag = false;
                    for (String split : splits) {
                        if (!split.isBlank()) {
                            if (!flag) {
                                aliasName2 = split.trim();
                                flag = true;
                            } else {
                                args2 = split.trim();
                            }
                        }
                    }
                    AliasWithArgs aliasWithArgs = Aliases.aliasesWithArgs.get(aliasName2);
                    if (aliasWithArgs != null)
                        aliases.add(new AliasWithArgsRecord(aliasWithArgs, args2));
                    break;
            }
        }
    }

    /**
     * do not override this when constructing a new instance,
     * <p>
     * may have some difficult progress but finally run some builtin aliases
     */
    @Override
    public void run(String args) {
        decodeArgs2Alias(this.args);
        for (AliasWithArgsRecord aliasRecord : aliases) {
            Alias alias = aliasRecord.alias();
            if (alias instanceof UserAlias userAlias) {
                userAlias.runInternal(aliasRecord.args(), this);
            } else {
                alias.run(aliasRecord.args());
            }
        }
    }

    public void runInternal(String args, Alias originUserAlias) {
        decodeArgs2Alias(this.args);
        for (AliasWithArgsRecord aliasRecord : aliases) {
            Alias alias = aliasRecord.alias();
            if (alias instanceof UserAlias userAlias) {
                if (userAlias == originUserAlias) {
                    //infinite loop is not allowed, just ignore them
                    continue;
                }
                userAlias.runInternal(aliasRecord.args(), originUserAlias);
            } else {
                alias.run(aliasRecord.args());
            }
        }
    }
}
