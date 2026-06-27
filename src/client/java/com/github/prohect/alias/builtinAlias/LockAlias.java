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
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

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
     * We use a unique code to avoid colliding with {@link InputUtil#UNKNOWN_KEY}
     * which is shared by all unbound vanilla key bindings.
     */
    private static final InputUtil.Key LOCK_PLACEHOLDER =
        InputUtil.Type.KEYSYM.createFromCode(Integer.MIN_VALUE);

    // Maps action type -> original saved bound key
    private static final Map<String, InputUtil.Key> savedBoundKeys =
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
    public static final Set<InputUtil.Key> LOCKED_PHYSICAL_KEYS =
        new HashSet<>();

    /**
     * Tracks which physical keys are locked for which alias name.
     * Used by the alias-lock feature so unlock can remove the right keys.
     */
    private static final Map<String, Set<InputUtil.Key>> LOCKED_ALIAS_KEYS =
        new HashMap<>();

    // ── alias-name-based locking ─────────────────────────────────────────

    /**
     * Lock all physical keys bound to the given alias name.
     * The alias can still be triggered via {@code runAlias}.
     */
    static void lockAliasByName(String aliasName) {
        if (LOCKED_ALIAS_KEYS.containsKey(aliasName)) return; // already locked
        Set<InputUtil.Key> keys = new HashSet<>();
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
                "[Lock]No keys bound to alias: {}",
                aliasName
            );
            return;
        }
        LOCKED_PHYSICAL_KEYS.addAll(keys);
        LOCKED_ALIAS_KEYS.put(aliasName, keys);
        BindAliasPlusClient.LOGGER.info(
            "[Lock]Locked alias '{}' — {} key(s) blocked",
            aliasName,
            keys.size()
        );
    }

    /**
     * Unlock physical keys previously locked for the given alias name.
     */
    static void unlockAliasByName(String aliasName) {
        Set<InputUtil.Key> keys = LOCKED_ALIAS_KEYS.remove(aliasName);
        if (keys == null) {
            BindAliasPlusClient.LOGGER.warn(
                "[Lock]Alias not locked: {}",
                aliasName
            );
            return;
        }
        LOCKED_PHYSICAL_KEYS.removeAll(keys);
        BindAliasPlusClient.LOGGER.info(
            "[Lock]Unlocked alias '{}' — {} key(s) restored",
            aliasName,
            keys.size()
        );
    }

    // ── instance ──────────────────────────────────────────────────────

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

        if (lock) {
            lockAction(actionType);
        } else {
            unlockAction(actionType);
        }
        return this;
    }

    // ── shared helpers used by PlusLockAlias / UnlockAlias ─────────

    static void lockAction(String actionType) {
        KeyBinding keyBinding = getKeyBindingForAction(actionType);
        if (keyBinding == null) {
            // Not a vanilla action — try locking by alias name
            lockAliasByName(actionType);
            return;
        }
        if (!savedBoundKeys.containsKey(actionType)) {
            InputUtil.Key originalKey = keyBinding.boundKey;
            savedBoundKeys.put(actionType, originalKey);
            LOCKED_PHYSICAL_KEYS.add(originalKey);
            keyBinding.boundKey = LOCK_PLACEHOLDER;
            KeyBinding.updateKeysByCode();
            lockModBoundKeys(actionType);
        }
    }

    static void unlockAction(String actionType) {
        KeyBinding keyBinding = getKeyBindingForAction(actionType);
        if (keyBinding == null) {
            // Not a vanilla action — try unlocking by alias name
            unlockAliasByName(actionType);
            return;
        }
        InputUtil.Key savedKey = savedBoundKeys.remove(actionType);
        if (savedKey != null) {
            LOCKED_PHYSICAL_KEYS.remove(savedKey);
            keyBinding.boundKey = savedKey;
            KeyBinding.updateKeysByCode();
            unlockModBoundKeys(actionType);
        }
    }

    // ── mod-bound-key helpers ──────────────────────────────────────────

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

        Set<InputUtil.Key> keysToRemove = new HashSet<>();
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

        for (InputUtil.Key key : keysToRemove) {
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

    private static KeyBinding getKeyBindingForAction(String actionType) {
        GameOptions options = MinecraftClient.getInstance().options;
        String bare = actionType.startsWith(GAMEKEY_PREFIX)
            ? actionType.substring(GAMEKEY_PREFIX.length())
            : actionType;
        return switch (bare) {
            case "attack" -> options.attackKey;
            case "use" -> options.useKey;
            case "forward" -> options.forwardKey;
            case "back" -> options.backKey;
            case "left" -> options.leftKey;
            case "right" -> options.rightKey;
            case "jump" -> options.jumpKey;
            case "sneak" -> options.sneakKey;
            case "sprint" -> options.sprintKey;
            default -> null;
        };
    }
}
