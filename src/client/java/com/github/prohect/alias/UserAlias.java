package com.github.prohect.alias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.builtinAlias.AliasAlias;
import com.github.prohect.alias.builtinAlias.BindAlias;
import com.github.prohect.alias.builtinAlias.UnbindAlias;
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
    private boolean fromCFG = false;
    private boolean predefined = false;

    public UserAlias(String args) {
        this.args = args;
    }

    public UserAlias(String args, boolean fromAutoload) {
        this.args = args;
        this.fromCFG = fromAutoload;
    }

    /** Construct a predefined (protected) user alias that cannot be overwritten. */
    public UserAlias(String args, boolean fromCFG, boolean predefined) {
        this.args = args;
        this.fromCFG = fromCFG;
        this.predefined = predefined;
    }

    public boolean isFromCFG() {
        return fromCFG;
    }

    public boolean isPredefined() {
        return predefined;
    }

    public void setFromCFG(boolean fromAutoload) {
        this.fromCFG = fromAutoload;
    }

    /** @return the raw definition string used to create this alias */
    public String getDefinitionString() {
        return args;
    }

    private void decodeArgs2Alias(String args) {
        ArrayList<String> definitions = Alias.getDefinitions(args);
        for (String definition : definitions) {
            ArrayList<String> definitionSplits = Alias.getDefinitionSplits(definition);
            int count = definitionSplits.size();
            for (String split : definitionSplits)
                if (split.isBlank())
                    --count;
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
                    boolean onAliasName = true; // otherwise on alias definition
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
                            } else
                                args2.append(Alias.divider4AliasArgs).append(trimmed);
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
    @SuppressWarnings("DuplicatedCode")
    @Override
    public UserAlias run(String args) {
        decodeArgs2Alias(this.args);
        while (!aliases.isEmpty()) {
            AliasRecord aliasRecord = aliases.poll();
            Alias<?> alias = Alias.aliasesWithoutArgs.get(aliasRecord.aliasName());
            alias = alias == null ? Alias.aliasesWithoutArgs_notSuggested.get(aliasRecord.aliasName()) : alias;
            alias = alias == null ? Alias.aliasesWithArgs_notSuggested.get(aliasRecord.aliasName()) : alias;
            alias = alias == null ? Alias.aliasesWithArgs.get(aliasRecord.aliasName()) : alias;
            switch (alias) {
                case null -> {
                }
                case UserAlias userAlias -> userAlias.runInternal(List.of(this));
                case WaitAlias waitAlias -> {
                    StringBuilder definitionLeft = new StringBuilder();
                    AliasRecord aliasRecord1;
                    boolean firstItem = true;
                    while (!aliases.isEmpty()) {
                        aliasRecord1 = aliases.poll();
                        if (!firstItem) {
                            definitionLeft.append(Alias.divider4AliasDefinition);
                        }
                        definitionLeft.append(aliasRecord1.aliasName());
                        if (!aliasRecord1.args().isEmpty()) {
                            definitionLeft.append(Alias.divider4AliasArgs);
                            String argsStr = aliasRecord1.args();
                            var _alias = Alias.aliasesWithArgs.get(aliasRecord1.aliasName());
                            if (_alias instanceof AliasAlias || _alias instanceof BindAlias || _alias instanceof UnbindAlias) {
                                definitionLeft.append(argsStr.replace(BuiltinAliasWithGreedyStringArgs.divider4AliasDefinition,
                                        Alias.divider4AliasDefinition));
                            } else if (argsStr.contains(String.valueOf(Alias.divider4AliasDefinition))) {
                                definitionLeft.append('"').append(argsStr).append('"');
                            } else {
                                definitionLeft.append(argsStr);
                            }
                        }
                        firstItem = false;
                    }
                    waitAlias.run(aliasRecord.args(), definitionLeft.toString());
                    return this;
                }
                default -> {
                    if (alias instanceof BuiltinAliasWithArgs && Alias.blackList4Screen.contains(alias)) {
                        if (!Alias.isUnderAnyScreen()) {
                            alias.run(aliasRecord.args());
                            continue;
                        }
                        if (aliasRecord.args().equals("0")) {
                            alias.run(aliasRecord.args());
                        }
                    } else
                        alias.run(aliasRecord.args());
                }
            }
        }
        return this;
    }

    /**
     * @param userAliasesCallChains it's first element must be the rootAlias userAlias of the call chain
     */
    @SuppressWarnings("DuplicatedCode")
    public void runInternal(List<UserAlias> userAliasesCallChains) {
        decodeArgs2Alias(this.args);
        while (!aliases.isEmpty()) {
            AliasRecord aliasRecord = aliases.poll();
            Alias<?> alias = Alias.aliasesWithoutArgs.get(aliasRecord.aliasName());
            alias = alias == null ? Alias.aliasesWithoutArgs_notSuggested.get(aliasRecord.aliasName()) : alias;
            alias = alias == null ? Alias.aliasesWithArgs_notSuggested.get(aliasRecord.aliasName()) : alias;
            alias = alias == null ? Alias.aliasesWithArgs.get(aliasRecord.aliasName()) : alias;
            switch (alias) {
                case null -> {
                }
                case UserAlias userAlias -> {
                    if (userAliasesCallChains.contains(userAlias)) {
                        // infinite loop is not allowed, ignore them
                        BindAliasClient.LOGGER.warn("{}[switchSlot]infinite loop detected checking UserAliasesCallChains.",
                                BindAliasClient.tickPrefix());
                        continue;
                    }
                    userAlias.runInternal(Stream.concat(userAliasesCallChains.stream(), Stream.of(userAlias)).toList());
                }
                case WaitAlias waitAlias -> {
                    StringBuilder definitionLeft = new StringBuilder();
                    AliasRecord aliasRecord1;
                    boolean firstItem = true;
                    while (!aliases.isEmpty()) {
                        aliasRecord1 = aliases.poll();
                        if (!firstItem) {
                            definitionLeft.append(Alias.divider4AliasDefinition);
                        }
                        definitionLeft.append(aliasRecord1.aliasName());
                        if (!aliasRecord1.args().isEmpty()) {
                            definitionLeft.append(Alias.divider4AliasArgs);
                            String argsStr = aliasRecord1.args();
                            var _alias = Alias.aliasesWithArgs.get(aliasRecord1.aliasName());
                            if (_alias instanceof AliasAlias || _alias instanceof BindAlias || _alias instanceof UnbindAlias) {
                                definitionLeft.append(argsStr.replace(BuiltinAliasWithGreedyStringArgs.divider4AliasDefinition,
                                        Alias.divider4AliasDefinition));
                            } else if (argsStr.contains(String.valueOf(Alias.divider4AliasDefinition))) {
                                definitionLeft.append('"').append(argsStr).append('"');
                            } else {
                                definitionLeft.append(argsStr);
                            }
                        }
                        firstItem = false;
                    }
                    for (int i = userAliasesCallChains.size() - 1; i >= 0; i--) {
                        UserAlias rootAlias = userAliasesCallChains.get(i);
                        while (true) {
                            if (rootAlias.aliases.isEmpty())
                                break;
                            aliasRecord1 = rootAlias.aliases.poll();
                            if (!firstItem) {
                                definitionLeft.append(Alias.divider4AliasDefinition);
                            }
                            definitionLeft.append(aliasRecord1.aliasName());
                            if (!aliasRecord1.args().isEmpty()) {
                                definitionLeft.append(Alias.divider4AliasArgs);
                                String argsStr = aliasRecord1.args();
                                var _alias = Alias.aliasesWithArgs.get(aliasRecord1.aliasName());
                                if (_alias instanceof AliasAlias || _alias instanceof BindAlias
                                        || _alias instanceof UnbindAlias) {
                                    definitionLeft
                                            .append(argsStr.replace(BuiltinAliasWithGreedyStringArgs.divider4AliasDefinition,
                                                    Alias.divider4AliasDefinition));
                                } else if (argsStr.contains(String.valueOf(Alias.divider4AliasDefinition))) {
                                    definitionLeft.append('"').append(argsStr).append('"');
                                } else {
                                    definitionLeft.append(argsStr);
                                }
                            }
                            firstItem = false;
                        }
                    }
                    waitAlias.run(aliasRecord.args(), definitionLeft.toString());
                    return;
                }
                default -> {
                    if (alias instanceof BuiltinAliasWithArgs && Alias.blackList4Screen.contains(alias)) {
                        if (!Alias.isUnderAnyScreen()) {
                            alias.run(aliasRecord.args());
                            continue;
                        }
                        if (aliasRecord.args().equals("0")) {
                            alias.run(aliasRecord.args());
                        }
                    } else
                        alias.run(aliasRecord.args());
                }
            }
        }
    }
}
