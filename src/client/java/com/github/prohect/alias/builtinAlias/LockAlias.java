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
 * Builtin alias to temporarily lock a game key or custom alias,
 * preventing the user's physical key/mouse input from interfering
 * with an alias sequence.
 * <p>
 * Usage: {@code builtinLock\actionType\flag}
 * <ul>
 *   <li>{@code builtinLock\attack\1} — lock the attack key</li>
 *   <li>{@code builtinLock\attack\0} — unlock the attack key</li>
 * </ul>
 * <p>
 * Supported game-key types (use {@code gameKey:} prefix for suggestions):
 * attack, use, forward, back, left, right, jump, sneak, sprint
 * <p>
 * User-facing shortcuts are {@link LockAlias_OnLock} ({@code +lockKey\gameKey:attack})
 * and {@link LockAlias_Unlock} ({@code -lockKey\gameKey:attack}).
 * To lock a custom UserAlias, use {@code +lockKey\myAlias}.
 */
public class LockAlias extends BuiltinAliasWithArgs<LockAlias> {

    public LockAlias() {
        super("builtinLock");
    }

    /** Supported game-key action types, prefixed for command suggestions. */
    public static final List<String> SUPPORTED_ACTIONS = List.of(
        "gameKey:attack",
        "gameKey:use",
        "gameKey:forward",
        "gameKey:back",
        "gameKey:left",
        "gameKey:right",
        "gameKey:jump",
        "gameKey:sneak",
        "gameKey:sprint"
    );

    private static final String GAMEKEY_PREFIX = "gameKey:";

    /**
     * A sentinel key that won't match any physical keyboard/mouse input.
     * Uses {@link InputConstants#UNKNOWN} (GLFW_KEY_UNKNOWN = -1) which
     * GLFW handles gracefully (no "Invalid key" error) and Minecraft
     * already skips in {@code releaseAll()} / key polling.
     */
    private static final InputConstants.Key LOCK_PLACEHOLDER =
        InputConstants.UNKNOWN;

    // Maps action type -> original saved bound key
    private static final Map<String, InputConstants.Key> savedBoundKeys =
        new HashMap<>();

    /** Maps action type -> list of alias name patterns that directly trigger it */
    private static final Map<String, List<String>> ACTION_ALIAS_PATTERNS =
        new HashMap<>();

    static {
        for (String action : SUPPORTED_ACTIONS) {
            String bare = action.startsWith(GAMEKEY_PREFIX)
                ? action.substring(GAMEKEY_PREFIX.length())
                : action;
            List<String> patterns = new ArrayList<>();
            patterns.add("+" + bare);
            patterns.add("-" + bare);
            patterns.add(
                "builtin" +
                    bare.substring(0, 1).toUpperCase() +
                    bare.substring(1)
            );
            ACTION_ALIAS_PATTERNS.put(bare, patterns);
        }
    }

    /**
     * Physical keys currently locked. Mixins check this set to also block
     * keys registered in {@code BINDING_PLUS} during lock.
     */
    public static final Set<InputConstants.Key> LOCKED_PHYSICAL_KEYS =
        new HashSet<>();

    /**
     * Tracks which physical keys are locked for which alias name.
     * Used by the alias-lock feature so unlock can remove the right keys.
     */
    private static final Map<
        String,
        Set<InputConstants.Key>
    > LOCKED_ALIAS_KEYS = new HashMap<>();

    // ── alias-name-based locking (lockAlias / unlockAlias) ───────────────

    /**
     * Restore all locked game keys and clear all lock state.
     * Called on server disconnect to prevent stale key bindings.
     */
    public static void clearAllLocks() {
        for (String actionType : new HashSet<>(savedBoundKeys.keySet())) {
            unlockAction(actionType);
        }
        for (String aliasName : new HashSet<>(LOCKED_ALIAS_KEYS.keySet())) {
            unlockAliasByName(aliasName);
        }
        LOCKED_PHYSICAL_KEYS.clear();
        savedBoundKeys.clear();
        LOCKED_ALIAS_KEYS.clear();
    }

    /**
     * Lock all physical keys bound to the given alias name.
     * The alias can still be triggered via {@code runAlias}.
     */
    static void lockAliasByName(String aliasName) {
        if (LOCKED_ALIAS_KEYS.containsKey(aliasName)) return; // already locked
        Set<InputConstants.Key> keys = new HashSet<>();
        BindAliasPlusClient.BINDING_PLUS.forEach((key, binding) -> {
            if (
                aliasName.equals(binding.aliasNameOnKeyPressed()) ||
                aliasName.equals(binding.aliasNameOnKeyReleased())
            ) {
                keys.add(key);
            }
        });
        if (keys.isEmpty()) {
            BindAliasPlusClient.LOGGER.warn(
                "{}[Lock]No keys bound to alias: {}",
                BindAliasPlusClient.tickPrefix(),
                aliasName
            );
            return;
        }
        LOCKED_PHYSICAL_KEYS.addAll(keys);
        LOCKED_ALIAS_KEYS.put(aliasName, keys);
        BindAliasPlusClient.LOGGER.info(
            "{}[Lock]Locked alias '{}' — {} key(s) blocked",
            BindAliasPlusClient.tickPrefix(),
            aliasName,
            keys.size()
        );
    }

    /**
     * Unlock physical keys previously locked for the given alias name.
     */
    static void unlockAliasByName(String aliasName) {
        Set<InputConstants.Key> keys = LOCKED_ALIAS_KEYS.remove(aliasName);
        if (keys == null) {
            BindAliasPlusClient.LOGGER.warn(
                "{}[Lock]Alias not locked: {}",
                BindAliasPlusClient.tickPrefix(),
                aliasName
            );
            return;
        }
        LOCKED_PHYSICAL_KEYS.removeAll(keys);
        BindAliasPlusClient.LOGGER.info(
            "{}[Lock]Unlocked alias '{}' — {} key(s) restored",
            BindAliasPlusClient.tickPrefix(),
            aliasName,
            keys.size()
        );
    }

    // ── instance ──────────────────────────────────────────────────────

    @Override
    public LockAlias run(String args) {
        String[] parts = args.split(
            Pattern.quote(String.valueOf(Alias.divider4AliasArgs))
        );
        if (parts.length != 2) {
            BindAliasPlusClient.LOGGER.warn(
                "{}[Lock]Invalid arguments: {}",
                BindAliasPlusClient.tickPrefix(),
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
            // Not a vanilla action — try locking by alias name
            lockAliasByName(actionType);
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
            // Not a vanilla action — try unlocking by alias name
            unlockAliasByName(actionType);
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

                // Handle +lockKey\<action> / -lockKey\<action> — check the concrete action
                if (
                    ("+lockKey".equals(aliasPart) ||
                        "-lockKey".equals(aliasPart)) &&
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
        String bare = actionType.startsWith(GAMEKEY_PREFIX)
            ? actionType.substring(GAMEKEY_PREFIX.length())
            : actionType;
        return switch (bare) {
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
