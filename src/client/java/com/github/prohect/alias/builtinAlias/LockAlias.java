package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyBindingPlus;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.AliasWithoutArgs;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import com.github.prohect.alias.UserAlias;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;

/**
 * Builtin alias to temporarily lock a game action, preventing the user's
 * physical key/mouse input from interfering with an alias sequence.
 * <p>
 * Usage: {@code builtinLock\actionType\flag}
 * <ul>
 *   <li>{@code builtinLock\attack\1} — lock the attack key</li>
 *   <li>{@code builtinLock\attack\0} — unlock the attack key</li>
 * </ul>
 * <p>
 * Supported action types: attack, use, forward, back, left, right, jump, sneak, sprint
 * <p>
 * Shortcuts are registered as user aliases without args:
 * {@code +lock:attack}, {@code -lock:attack}, etc.
 */
public class LockAlias extends BuiltinAliasWithArgs<LockAlias> {

    /**
     * A sentinel key that won't match any physical keyboard/mouse input.
     * We use a unique code to avoid colliding with {@link InputUtil#UNKNOWN_KEY}
     * which is shared by all unbound vanilla key bindings.
     */
    private static final InputConstants.Key LOCK_PLACEHOLDER =
        InputConstants.Type.KEYSYM.getOrCreate(Integer.MIN_VALUE);

    // Maps action type -> original saved bound key
    private static final Map<String, InputConstants.Key> savedBoundKeys =
        new HashMap<>();

    /** Maps action type -> list of alias name patterns that directly trigger it */
    private static final Map<String, List<String>> ACTION_ALIAS_PATTERNS =
        new HashMap<>();

    static {
        for (String action : new String[] {
            "attack",
            "use",
            "forward",
            "back",
            "left",
            "right",
            "jump",
            "sneak",
            "sprint",
        }) {
            List<String> patterns = new ArrayList<>();
            patterns.add("+" + action);
            patterns.add("-" + action);
            patterns.add(
                "builtin" +
                    action.substring(0, 1).toUpperCase() +
                    action.substring(1)
            );
            ACTION_ALIAS_PATTERNS.put(action, patterns);
        }
    }

    /**
     * Physical keys currently locked. Mixins check this set to also block
     * keys registered in {@code BINDING_PLUS} during lock.
     */
    public static final Set<InputConstants.Key> LOCKED_PHYSICAL_KEYS =
        new HashSet<>();

    @Override
    public LockAlias run(String args) {
        // args pattern: actionType\flag (e.g. "attack\1" or "attack\0")
        String[] parts = args.split(
            Pattern.quote(String.valueOf(Alias.divider4AliasArgs))
        );
        if (parts.length != 2) {
            BindAliasPlusClient.LOGGER.warn(
                "[Lock]Invalid arguments: {}",
                args
            );
            return this;
        }
        String actionType = parts[0];
        boolean lock = "1".equals(parts[1]);

        KeyMapping keyBinding = getKeyBindingForAction(actionType);
        if (keyBinding == null) {
            BindAliasPlusClient.LOGGER.warn(
                "[Lock]Unknown action type: {}",
                actionType
            );
            return this;
        }

        if (lock) {
            if (!savedBoundKeys.containsKey(actionType)) {
                // Save and replace vanilla bound key
                InputConstants.Key originalKey = keyBinding.key;
                savedBoundKeys.put(actionType, originalKey);
                LOCKED_PHYSICAL_KEYS.add(originalKey);
                keyBinding.key = LOCK_PLACEHOLDER;
                KeyMapping.resetMapping();

                // Also lock any mod-bound keys whose aliases target this action
                lockModBoundKeys(actionType);
            }
        } else {
            InputConstants.Key savedKey = savedBoundKeys.remove(actionType);
            if (savedKey != null) {
                LOCKED_PHYSICAL_KEYS.remove(savedKey);
                keyBinding.key = savedKey;
                KeyMapping.resetMapping();

                // Remove mod-bound keys for this action
                unlockModBoundKeys(actionType);
            }
        }
        return this;
    }

    /**
     * Scan {@code BINDING_PLUS} for keys whose bound aliases (directly or
     * through UserAlias definitions) target the given action, and add those
     * physical keys to {@link #LOCKED_PHYSICAL_KEYS}.
     */
    private static void lockModBoundKeys(String actionType) {
        List<String> patterns = ACTION_ALIAS_PATTERNS.get(actionType);
        if (patterns == null) return;

        BindAliasPlusClient.BINDING_PLUS.forEach((key, binding) -> {
            if (
                aliasTargetsLockedAction(
                    binding.aliasNameOnKeyPressed(),
                    patterns
                ) ||
                aliasTargetsLockedAction(
                    binding.aliasNameOnKeyReleased(),
                    patterns
                )
            ) {
                LOCKED_PHYSICAL_KEYS.add(key);
            }
        });
    }

    /**
     * Remove from {@link #LOCKED_PHYSICAL_KEYS} any keys that were added
     * solely because of this action type's mod bindings. A key is kept if
     * it is still needed for another locked action's vanilla boundKey.
     */
    private static void unlockModBoundKeys(String actionType) {
        List<String> patterns = ACTION_ALIAS_PATTERNS.get(actionType);
        if (patterns == null) return;

        Set<InputConstants.Key> keysToRemove = new HashSet<>();
        BindAliasPlusClient.BINDING_PLUS.forEach((key, binding) -> {
            if (
                aliasTargetsLockedAction(
                    binding.aliasNameOnKeyPressed(),
                    patterns
                ) ||
                aliasTargetsLockedAction(
                    binding.aliasNameOnKeyReleased(),
                    patterns
                )
            ) {
                keysToRemove.add(key);
            }
        });

        // Only remove keys that aren't still needed by another active lock
        for (InputConstants.Key key : keysToRemove) {
            boolean stillNeeded = false;
            for (String otherAction : savedBoundKeys.keySet()) {
                if (otherAction.equals(actionType)) continue;
                List<String> otherPatterns = ACTION_ALIAS_PATTERNS.get(
                    otherAction
                );
                if (otherPatterns == null) continue;
                KeyBindingPlus binding = BindAliasPlusClient.BINDING_PLUS.get(
                    key
                );
                if (
                    binding != null &&
                    (aliasTargetsLockedAction(
                            binding.aliasNameOnKeyPressed(),
                            otherPatterns
                        ) ||
                        aliasTargetsLockedAction(
                            binding.aliasNameOnKeyReleased(),
                            otherPatterns
                        ))
                ) {
                    stillNeeded = true;
                    break;
                }
            }
            if (!stillNeeded) {
                LOCKED_PHYSICAL_KEYS.remove(key);
            }
        }
    }

    /**
     * Check whether the given alias name (directly or through its UserAlias
     * definition) targets any of the locked patterns.
     */
    private static boolean aliasTargetsLockedAction(
        String aliasName,
        List<String> patterns
    ) {
        if (aliasName == null || aliasName.isEmpty()) return false;

        // Direct match: alias name itself is one of the patterns
        if (patterns.contains(aliasName)) return true;

        // Indirect: look up as a UserAlias and check its definition
        AliasWithoutArgs<?> alias = Alias.aliasesWithoutArgs.get(aliasName);
        if (alias == null) {
            alias = Alias.aliasesWithoutArgs_notSuggested.get(aliasName);
        }
        if (alias == null) {
            alias = Alias.aliasesWithoutArgs_fromBindCommand.get(aliasName);
        }
        if (alias instanceof UserAlias userAlias) {
            // Check if the alias definition contains any locked pattern
            // (split by definition divider to match whole tokens)
            String def = userAlias.getDefinitionString();
            for (String token : def.split(
                Pattern.quote(String.valueOf(Alias.divider4AliasDefinition))
            )) {
                // The token may have args (e.g. "builtinAttack\1")
                String aliasPart = token.split(
                    Pattern.quote(String.valueOf(Alias.divider4AliasArgs))
                )[0];
                if (patterns.contains(aliasPart)) return true;
            }
        }
        return false;
    }

    private static KeyMapping getKeyBindingForAction(String actionType) {
        Options options = Minecraft.getInstance().options;
        return switch (actionType) {
            case "attack" -> options.keyAttack;
            case "use" -> options.keyUse;
            case "forward" -> options.keyUp;
            case "back" -> options.keyDown;
            case "left" -> options.keyLeft;
            case "right" -> options.keyRight;
            case "jump" -> options.keyJump;
            case "sneak" -> options.keyShift;
            case "sprint" -> options.keySprint;
            default -> null;
        };
    }
}
