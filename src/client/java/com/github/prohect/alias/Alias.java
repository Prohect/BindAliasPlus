package com.github.prohect.alias;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public interface Alias {
    List<Alias> blackList4lockCursor = new ArrayList<>();
    HashMap<String, AliasWithoutArgs> aliasesWithoutArgs = new HashMap<>();
    HashMap<String, AliasWithoutArgs> aliasesWithoutArgs_fromBindCommand = new HashMap<>();
    HashMap<String, AliasWithArgs> aliasesWithArgs = new HashMap<>();
    String divider4AliasDefinition = " ";
    String divider4AliasArgs = "\\";

    static String getOppositeDefinition(String definition) {
        StringBuilder oppositeDefinition = new StringBuilder();
        String[] definitions = definition.split(Pattern.quote(Alias.divider4AliasDefinition));
        Arrays.stream(definitions).forEach(def -> {
            if (def.startsWith("+")) {
                oppositeDefinition.append("-").append(def.substring(1)).append(Alias.divider4AliasDefinition);
            } else if (def.startsWith("-")) {
                oppositeDefinition.append("+").append(def.substring(1)).append(Alias.divider4AliasDefinition);
            }
        });
        return oppositeDefinition.toString();
    }

    void run(String args);

    @SuppressWarnings("UnusedReturnValue")
    default Alias addToLockCursorBlackList() {
        blackList4lockCursor.add(this);
        return this;
    }

}
