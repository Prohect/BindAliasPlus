package com.github.prohect.alias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.CommandBlockEditScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import org.jetbrains.annotations.NotNull;

public interface Alias<T extends Alias<T>> {
    List<Alias<?>> blackList4Screen = new ArrayList<>();
    HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs = new HashMap<>();
    HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_notSuggested = new HashMap<>();
    HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_fromBindCommand = new HashMap<>();
    HashMap<String, AliasWithArgs<?>> aliasesWithArgs = new HashMap<>();
    HashMap<String, AliasWithArgs<?>> aliasesWithArgs_notSuggested = new HashMap<>();
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
                if (c == '"') {
                    coveredByDoubleQuotes = !coveredByDoubleQuotes;
                } else
                    currentDefinition.append(c);
                lastStepSubmit = false;
            } else {
                if (coveredByDoubleQuotes) {
                    currentDefinition.append(c);
                    continue;
                }
                if (lastStepSubmit)
                    continue;
                definitions.add(currentDefinition.toString());
                currentDefinition = new StringBuilder();
                lastStepSubmit = true;
            }
        }
        if (!currentDefinition.isEmpty()) {
            definitions.add(currentDefinition.toString());
        }
        return definitions;
    }

    static @NotNull ArrayList<String> getDefinitionSplits(String definition) {
        ArrayList<String> definitionSplits = new ArrayList<>();
        StringBuilder currentDefinition = new StringBuilder();
        boolean coveredByDoubleQuotes = false;
        boolean lastStepSubmit = false;
        for (char c : definition.toCharArray()) {
            if (c != Alias.divider4AliasArgs) {
                if (c == '"') {
                    coveredByDoubleQuotes = !coveredByDoubleQuotes;
                } else
                    currentDefinition.append(c);
                lastStepSubmit = false;
            } else {
                if (coveredByDoubleQuotes) {
                    currentDefinition.append(c);
                    continue;
                }
                if (lastStepSubmit)
                    continue;
                definitionSplits.add(currentDefinition.toString());
                currentDefinition = new StringBuilder();
                lastStepSubmit = true;
            }
        }
        if (!currentDefinition.isEmpty()) {
            definitionSplits.add(currentDefinition.toString());
        }
        // Filter out empty/blank splits caused by trailing backslashes
        definitionSplits.removeIf(String::isBlank);
        return definitionSplits;
    }

    T run(String args);

    @SuppressWarnings({"unchecked", "UnusedReturnValue"})
    default T addToScreenBlackList() {
        blackList4Screen.add(this);
        return (T) this;
    }

    // ---- Screen-type helpers (backed by BindAliasPlusClient.currentScreen) ----

    static Screen getCurrentScreen() {
        return com.github.prohect.BindAliasPlusClient.currentScreen;
    }

    static boolean isUnderTextInputScreen() {
        Screen s = getCurrentScreen();
        return (s instanceof ChatScreen || s instanceof CommandBlockEditScreen || s instanceof SignEditScreen
                || s instanceof BookEditScreen);
    }

    static boolean isUnderAnyScreen() {
        return getCurrentScreen() != null;
    }

    static boolean isInContainerScreen() {
        return getCurrentScreen() instanceof AbstractContainerScreen;
    }

    static boolean isInInventoryScreen() {
        return getCurrentScreen() instanceof InventoryScreen;
    }

    static boolean isInCreativeInventoryScreen() {
        return getCurrentScreen() instanceof CreativeModeInventoryScreen;
    }
}
