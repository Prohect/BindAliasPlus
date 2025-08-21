package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.builtinAlias.WaitAlias;

import java.util.ArrayDeque;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * a userAlias could not have args
 */
public final class UserAlias implements AliasWithoutArgs {
    final ArrayDeque<AliasRecord> aliases = new ArrayDeque<>();
    final String args;

    public UserAlias(String args) {
        this.args = args;
    }

    private void decodeArgs2Alias(String args) {
        String[] strings = args.split(Pattern.quote(Alias.divider4AliasDefinition));
        for (String aliasNameAndArgs : strings) {
            String[] splits = aliasNameAndArgs.split(Pattern.quote(Alias.divider4AliasArgs));
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
                    AliasWithoutArgs aliasWithoutArgs = Alias.aliasesWithoutArgs.get(aliasName);
                    if (aliasWithoutArgs != null)
                        aliases.add(new AliasRecord(aliasWithoutArgs, "", aliasName));
                    else BindAliasPlusClient.LOGGER.info("Alias with name {} not found.", aliasName);
                    break;
                default:
                    String aliasName2 = "";
                    StringBuilder args2 = new StringBuilder();
                    boolean flag = false;
                    boolean flag1 = false;
                    for (String split : splits) {
                        if (!split.isBlank()) {
                            if (!flag) {
                                aliasName2 = split.trim();
                                flag = true;
                            } else {
                                if (!flag1) {
                                    args2.append(split.trim());
                                    flag1 = true;
                                } else {
                                    args2.append(Alias.divider4AliasArgs).append(split.trim());
                                }
                            }
                        }
                    }
                    AliasWithArgs aliasWithArgs = Alias.aliasesWithArgs.get(aliasName2);
                    if (aliasWithArgs != null)
                        aliases.add(new AliasRecord(aliasWithArgs, args2.toString(), aliasName2));
                    else BindAliasPlusClient.LOGGER.info("Alias  with name {} not found.", aliasName2);
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
        while (!aliases.isEmpty()) {
            AliasRecord aliasRecord = aliases.poll();
            Alias alias = aliasRecord.alias();
            if (alias instanceof UserAlias userAlias) {
                userAlias.runInternal(List.of(this));
            } else if (alias instanceof WaitAlias waitAlias) {
                StringBuilder definitionLeft = new StringBuilder();
                AliasRecord aliasRecord1;
                while (!aliases.isEmpty()) {
                    aliasRecord1 = aliases.poll();
                    definitionLeft.append(Alias.divider4AliasDefinition).append(aliasRecord1.aliasName()).append(Alias.divider4AliasArgs).append(aliasRecord1.args());
                }
                waitAlias.run(aliasRecord.args(), definitionLeft.toString());
                return;
            } else {
                alias.run(aliasRecord.args());
            }
        }
    }

    /**
     * @param userAliasesCallChains it's first element must be the rootAlias userAlias of the call chain
     */
    public void runInternal(List<UserAlias> userAliasesCallChains) {
        decodeArgs2Alias(this.args);
        while (!aliases.isEmpty()) {
            AliasRecord aliasRecord = aliases.poll();
            Alias alias = aliasRecord.alias();
            if (alias instanceof UserAlias userAlias) {
                if (userAliasesCallChains.contains(userAlias)) {
                    //infinite loop is not allowed,  ignore them
                    BindAliasPlusClient.LOGGER.warn("[switchSlot]infinite loop detected checking UserAliasesCallChains.");
                    continue;
                }
                userAlias.runInternal(Stream.concat(userAliasesCallChains.stream(), Stream.of(userAlias)).toList());
            } else if (alias instanceof WaitAlias waitAlias) {
                StringBuilder definitionLeft = new StringBuilder();
                AliasRecord aliasRecord1;
                while (!aliases.isEmpty()) {
                    aliasRecord1 = aliases.poll();
                    definitionLeft.append(Alias.divider4AliasDefinition).append(aliasRecord1.aliasName()).append(Alias.divider4AliasArgs).append(aliasRecord1.args());
                }
                while (true) {
                    UserAlias rootAlias = userAliasesCallChains.getFirst();
                    if (rootAlias.aliases.isEmpty()) break;
                    aliasRecord1 = rootAlias.aliases.poll();
                    definitionLeft.append(Alias.divider4AliasDefinition).append(aliasRecord1.aliasName()).append(Alias.divider4AliasArgs).append(aliasRecord1.args());
                }
                waitAlias.run(aliasRecord.args(), definitionLeft.toString());
                return;
            } else {
                alias.run(aliasRecord.args());
            }
        }
    }
}
