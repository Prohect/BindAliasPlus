package com.github.prohect.alias;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Alias<T extends Alias<T>> {
    List<Alias<?>> blackList4lockCursor = new ArrayList<>();
    HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs = new HashMap<>();
    HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_fromBindCommand = new HashMap<>();
    HashMap<String, AliasWithArgs<?>> aliasesWithArgs = new HashMap<>();
    char divider4AliasDefinition = ' ';
    char divider4AliasArgs = '\\';

    /**
     * ignore blocks covered by double quotes
     */
    static String getOppositeDefinition(String args) {
        StringBuilder oppositeDefinition = new StringBuilder();
        ArrayList<String> definitions = getDefinitions(args);
        definitions.forEach(definition -> {
            if (definition.startsWith("+")) {
                oppositeDefinition.append("-").append(definition.substring(1)).append(Alias.divider4AliasDefinition);
            } else if (definition.startsWith("-")) {
                oppositeDefinition.append("+").append(definition.substring(1)).append(Alias.divider4AliasDefinition);
            }
        });
        return oppositeDefinition.toString();
    }

    static @NotNull ArrayList<String> getDefinitions(String args) {
        ArrayList<String> definitions = new ArrayList<>();
        StringBuilder currentDefinition = new StringBuilder();
        boolean coveredByDoubleQuotes = false;
        boolean lastStepSubmit = false;
        for (char c : args.toCharArray()) {
            if (c != Alias.divider4AliasDefinition) {
                if (c == '"') coveredByDoubleQuotes = !coveredByDoubleQuotes;
                else currentDefinition.append(c);
                lastStepSubmit = false;
            } else {
                if (coveredByDoubleQuotes) {
                    currentDefinition.append(c);
                    continue;
                }
                if (lastStepSubmit) continue;
                definitions.add(currentDefinition.toString());
                currentDefinition = new StringBuilder();
                lastStepSubmit = true;
            }
        }
        if (!currentDefinition.isEmpty()) definitions.add(currentDefinition.toString());
        return definitions;
    }

    static @NotNull ArrayList<String> getDefinitionSplits(String definition) {
        ArrayList<String> definitionSplits = new ArrayList<>();
        StringBuilder currentDefinition = new StringBuilder();
        boolean coveredByDoubleQuotes = false;
        boolean lastStepSubmit = false;
        for (char c : definition.toCharArray()) {
            if (c != Alias.divider4AliasArgs) {
                if (c == '"') coveredByDoubleQuotes = !coveredByDoubleQuotes;
                else currentDefinition.append(c);
                lastStepSubmit = false;
            } else {
                if (coveredByDoubleQuotes) {
                    currentDefinition.append(c);
                    continue;
                }
                if (lastStepSubmit) continue;
                definitionSplits.add(currentDefinition.toString());
                currentDefinition = new StringBuilder();
                lastStepSubmit = true;
            }
        }
        if (!currentDefinition.isEmpty()) definitionSplits.add(currentDefinition.toString());
        return definitionSplits;
    }

    T run(String args);

    @SuppressWarnings({"unchecked", "UnusedReturnValue"})
    default T addToLockCursorBlackList() {
        blackList4lockCursor.add(this);
        return (T) this;
    }

}
