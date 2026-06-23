package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.KeyBindingPlus;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.AliasWithoutArgs;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import com.github.prohect.alias.UserAlias;
import com.mojang.blaze3d.platform.InputConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

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
 * User-facing shortcuts are {@link LockAlias_OnLock} ({@code +lock\attack}) and
 * {@link LockAlias_Unlock} ({@code -lock\attack}), registered as suggested
 * aliases with args.
 */
public class LockAlias extends BuiltinAliasWithArgs<LockAlias> {

    /** Supported action types, exposed for command suggestions. */
    public static final List<String> SUPPORTED_ACTIONS = List.of(
        "attack",
        "use",
        "forward",
        "back",
        "left",
        "right",
        "jump",
        "sneak",
        "sprint"
    );

    /**
     * A sentinel key that won't match any physical keyboard/mouse input.
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
        for (String action : SUPPORTED_ACTIONS) {
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

    // ── instance ──────────────────────────────────────────────────────

    @Override
    public LockAlias run(String args) {
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

        if (lock) {
            lockAction(actionType);
        } else {
            unlockAction(actionType);
        }
        return this;
    }

    // ── shared helpers used by LockAlias_OnLock / LockAlias_Unlock ────

    static void lockAction(String actionType) {
        KeyMapping keyBinding = getKeyBindingForAction(actionType);
        if (keyBinding == null) {
            BindAliasPlusClient.LOGGER.warn(
                "[Lock]Unknown action type: {}",
                actionType
            );
            return;
        }
        if (!savedBoundKeys.containsKey(actionType)) {
            InputConstants.Key originalKey = keyBinding.key;
            savedBoundKeys.put(actionType, originalKey);
            LOCKED_PHYSICAL_KEYS.add(originalKey);
            keyBinding.key = LOCK_PLACEHOLDER;
            KeyMapping.resetMapping();
            lockModBoundKeys(actionType);
        }
    }

    static void unlockAction(String actionType) {
        KeyMapping keyBinding = getKeyBindingForAction(actionType);
        if (keyBinding == null) {
            BindAliasPlusClient.LOGGER.warn(
                "[Lock]Unknown action type: {}",
                actionType
            );
            return;
        }
        InputConstants.Key savedKey = savedBoundKeys.remove(actionType);
        if (savedKey != null) {
            LOCKED_PHYSICAL_KEYS.remove(savedKey);
            keyBinding.key = savedKey;
            KeyMapping.resetMapping();
            unlockModBoundKeys(actionType);
        }
    }

    // ── mod-bound-key helpers ──────────────────────────────────────────

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

    private static boolean aliasTargetsLockedAction(
        String aliasName,
        List<String> patterns
    ) {
        if (aliasName == null || aliasName.isEmpty()) return false;

        if (patterns.contains(aliasName)) return true;

        AliasWithoutArgs<?> alias = Alias.aliasesWithoutArgs.get(aliasName);
        if (alias == null) {
            alias = Alias.aliasesWithoutArgs_fromBindCommand.get(aliasName);
        }
        if (alias instanceof UserAlias userAlias) {
            String def = userAlias.getDefinitionString();
            for (String token : def.split(
                Pattern.quote(String.valueOf(Alias.divider4AliasDefinition))
            )) {
                String[] tokenParts = token.split(
                    Pattern.quote(String.valueOf(Alias.divider4AliasArgs))
                );
                String aliasPart = tokenParts[0];

                // Handle +lock\<action> / -lock\<action> — check the concrete action
                if (
                    ("+lock".equals(aliasPart) || "-lock".equals(aliasPart)) &&
                    tokenParts.length >= 2
                ) {
                    String lockActionName = tokenParts[1];
                    for (String pattern : patterns) {
                        String barePattern =
                            pattern.startsWith("+") || pattern.startsWith("-")
                                ? pattern.substring(1)
                                : pattern;
                        if (barePattern.equals(lockActionName)) return true;
                    }
                }
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
