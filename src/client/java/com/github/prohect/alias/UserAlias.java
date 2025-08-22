package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.builtinAlias.WaitAlias;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * a userAlias could not have definition
 */
public final class UserAlias implements AliasWithoutArgs<UserAlias> {
    final ArrayDeque<AliasRecord> aliases = new ArrayDeque<>();
    final String args;

    public UserAlias(String args) {
        this.args = args;
    }

    private void decodeArgs2Alias(String args) {
        ArrayList<String> definitions = Alias.getDefinitions(args);
        for (String definition : definitions) {
            ArrayList<String> definitionSplits = Alias.getDefinitionSplits(definition);
            int count = definitionSplits.size();
            for (String split : definitionSplits) if (split.isBlank()) --count;
            switch (count) {
                case 0:
                    break;
                case 1:
                    String aliasName = "";
                    for (String definitionSplit : definitionSplits) {
                        if (!definitionSplit.isBlank()) {
                            aliasName = definitionSplit.trim();
                            break;
                        }
                    }
                    aliases.add(new AliasRecord("", aliasName));
                    break;
                default:
                    String aliasName2 = "";
                    StringBuilder args2 = new StringBuilder();
                    boolean onAliasName = true;//otherwise on alias definition
                    boolean needDivider = false;
                    for (String definitionSplit : definitionSplits)
                        if (!definitionSplit.isBlank()) {
                            String trimmed = definitionSplit.trim();
                            if (onAliasName) {
                                aliasName2 = trimmed;
                                onAliasName = false;
                            } else if (!needDivider) {
                                args2.append(trimmed);
                                needDivider = true;
                            } else args2.append(Alias.divider4AliasArgs).append(trimmed);
                        }
                    aliases.add(new AliasRecord(args2.toString(), aliasName2));
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
    public UserAlias run(String args) {
        decodeArgs2Alias(this.args);
        while (!aliases.isEmpty()) {
            AliasRecord aliasRecord = aliases.poll();
            Alias<?> alias = Alias.aliasesWithoutArgs.get(aliasRecord.aliasName());
            alias = alias == null ? Alias.aliasesWithArgs.get(aliasRecord.aliasName()) : alias;
            switch (alias) {
                case null -> {
                }
                case UserAlias userAlias -> userAlias.runInternal(List.of(this));
                case WaitAlias waitAlias -> {
                    StringBuilder definitionLeft = new StringBuilder();
                    AliasRecord aliasRecord1;
                    while (!aliases.isEmpty()) {
                        aliasRecord1 = aliases.poll();
                        definitionLeft.append(Alias.divider4AliasDefinition).append(aliasRecord1.aliasName()).append(Alias.divider4AliasArgs).append(aliasRecord1.args());
                    }
                    waitAlias.run(aliasRecord.args(), definitionLeft.toString());
                    return this;
                }
                default -> alias.run(aliasRecord.args());
            }
        }
        return this;
    }

    /**
     * @param userAliasesCallChains it's first element must be the rootAlias userAlias of the call chain
     */
    public void runInternal(List<UserAlias> userAliasesCallChains) {
        decodeArgs2Alias(this.args);
        while (!aliases.isEmpty()) {
            AliasRecord aliasRecord = aliases.poll();
            Alias<?> alias = Alias.aliasesWithoutArgs.get(aliasRecord.aliasName());
            alias = alias == null ? Alias.aliasesWithArgs.get(aliasRecord.aliasName()) : alias;
            switch (alias) {
                case null -> {
                }
                case UserAlias userAlias -> {
                    if (userAliasesCallChains.contains(userAlias)) {
                        //infinite loop is not allowed,  ignore them
                        BindAliasPlusClient.LOGGER.warn("[switchSlot]infinite loop detected checking UserAliasesCallChains.");
                        continue;
                    }
                    userAlias.runInternal(Stream.concat(userAliasesCallChains.stream(), Stream.of(userAlias)).toList());
                }
                case WaitAlias waitAlias -> {
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
                }
                default -> alias.run(aliasRecord.args());
            }
        }
    }
}
