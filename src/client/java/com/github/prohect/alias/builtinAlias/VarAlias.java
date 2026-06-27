package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

/**
 * VarAlias - Store and retrieve in-game variables
 *
 * Usage:
 * - var\varName\source - Store a value from a source into varName
 *
 * Sources:
 * - "hotbarSlot" or "selectedSlot" - Current hotbar slot (1-9)
 * - "itemsOfSlot0" to "itemsOfSlot9" - Item count in slot (0=offhand, 1-9=hotbar)
 * - "pitch" - Player's current pitch angle (float)
 * - "yaw" - Player's current yaw angle (float)
 * - A number (e.g., 5, 3.14) - Direct integer or float value
 *
 * Examples:
 * - var\mySlot\hotbarSlot - Store current hotbar slot
 * - var\backup\5 - Store the number 5
 * - var\myPitch\pitch - Store current pitch angle
 * - var\myYaw\yaw - Store current yaw angle
 * - var\arrowCount\itemsOfSlot2 - Store item count from hotbar slot 2
 */
public class VarAlias extends BuiltinAliasWithArgs<VarAlias> {

    public VarAlias() {
        super("var");
    }

    // Global variable storage
    public static final Map<String, Number> VARIABLES = new HashMap<>();

    // Track which variables were loaded from config file
    public static final java.util.Set<String> AUTOLOADED_VARIABLES =
        new java.util.HashSet<>();

    // Pattern to check if variable name starts with a number (not allowed)
    private static final Pattern STARTS_WITH_NUMBER = Pattern.compile(
        "^[0-9].*"
    );

    @Override
    public VarAlias run(String args) {
        ArrayList<String> argsList = Alias.getDefinitionSplits(args);

        if (argsList.size() < 2) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Invalid arguments: expected varName and source. Usage: var\\varName\\source"
            );
            return this;
        }

        String varName = argsList.get(0).trim();
        String source = argsList.get(1).trim();

        // Validate variable name
        if (!isValidVarName(varName)) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Invalid variable name '{}': variable names cannot start with a number",
                varName
            );
            return this;
        }

        // Get value from source
        Number value = getValueFromSource(source);
        if (value == null) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Failed to get value from source '{}'",
                source
            );
            return this;
        }

        // Store variable
        VARIABLES.put(varName, value);
        BindAliasPlusClient.LOGGER.info(
            "[var] Variable '{}' set to {}",
            varName,
            value
        );

        return this;
    }

    /**
     * Run the var alias with autoload tracking
     */
    public VarAlias run(String args, boolean fromAutoload) {
        ArrayList<String> argsList = Alias.getDefinitionSplits(args);

        if (argsList.size() < 2) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Invalid arguments: expected varName and source. Usage: var\\varName\\source"
            );
            return this;
        }

        String varName = argsList.get(0).trim();
        String source = argsList.get(1).trim();

        // Validate variable name
        if (!isValidVarName(varName)) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Invalid variable name '{}': variable names cannot start with a number",
                varName
            );
            return this;
        }

        // Get value from source
        Number value = getValueFromSource(source);
        if (value == null) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Failed to get value from source '{}'",
                source
            );
            return this;
        }

        // Store variable
        VARIABLES.put(varName, value);

        // Track if from autoload
        if (fromAutoload) {
            AUTOLOADED_VARIABLES.add(varName);
        } else {
            AUTOLOADED_VARIABLES.remove(varName);
        }

        BindAliasPlusClient.LOGGER.info(
            "[var] Variable '{}' set to {}",
            varName,
            value
        );

        return this;
    }

    /**
     * Validate variable name - cannot start with a number
     */
    private boolean isValidVarName(String varName) {
        if (varName == null || varName.isEmpty()) {
            return false;
        }
        return !STARTS_WITH_NUMBER.matcher(varName).matches();
    }

    /**
     * Get numeric value from various sources
     * Returns Integer for integral sources, Double for floating-point sources
     */
    private Number getValueFromSource(String source) {
        // Check if source is a game variable reference
        if (
            "hotbarSlot".equalsIgnoreCase(source) ||
            "selectedSlot".equalsIgnoreCase(source)
        ) {
            return getCurrentHotbarSlot();
        }

        // Check if source is itemsOfSlot pattern (itemsOfSlot0 to itemsOfSlot9)
        if (source.toLowerCase().startsWith("itemsofslot")) {
            return getItemCountFromSlot(source);
        }

        // Check for pitch/yaw
        if ("pitch".equalsIgnoreCase(source)) {
            return getPlayerPitch();
        }
        if ("yaw".equalsIgnoreCase(source)) {
            return getPlayerYaw();
        }

        // Try to parse as a number: prefer integer if possible, otherwise double
        try {
            return Integer.parseInt(source);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(source);
            } catch (NumberFormatException e2) {
                BindAliasPlusClient.LOGGER.error(
                    "[var] Unknown source '{}' - expected 'hotbarSlot', 'selectedSlot', 'itemsOfSlot0-9', 'pitch', 'yaw', or a number",
                    source
                );
                return null;
            }
        }
    }

    /**
     * Get current hotbar slot (1-9 format to match mod conventions)
     */
    private Integer getCurrentHotbarSlot() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[var] Player is null");
            return null;
        }

        PlayerInventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasPlusClient.LOGGER.warn("[var] Inventory is null");
            return null;
        }

        // getSelectedSlot returns 0-8, we add 1 to match the mod's 1-9 convention
        return inventory.selectedSlot + 1;
    }

    /**
     * Get item count from a slot using "itemsOfSlot0-9" pattern
     * 0 = offhand, 1-9 = hotbar slots
     */
    private Integer getItemCountFromSlot(String source) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[var] Player is null");
            return null;
        }

        PlayerInventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasPlusClient.LOGGER.warn("[var] Inventory is null");
            return null;
        }

        // Extract slot number from "itemsOfSlotN"
        String slotStr = source.substring("itemsOfSlot".length());
        int slotIndex;
        try {
            slotIndex = Integer.parseInt(slotStr);
        } catch (NumberFormatException e) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Invalid slot number in '{}' - expected itemsOfSlot0 to itemsOfSlot9",
                source
            );
            return null;
        }

        // Validate slot range (0 for offhand, 1-9 for hotbar)
        if (slotIndex < 0 || slotIndex > 9) {
            BindAliasPlusClient.LOGGER.error(
                "[var] Slot number out of range in '{}' - must be 0-9 (0=offhand, 1-9=hotbar)",
                source
            );
            return null;
        }

        // Get the item stack
        // Slot mapping: 0=offhand (internal slot 40), 1-9=hotbar (internal slots 0-8)
        ItemStack stack;
        if (slotIndex == 0) {
            // Offhand slot (internal inventory index 40)
            stack = inventory.getStack(40);
        } else {
            // Hotbar slots (1-9 maps to inventory index 0-8)
            stack = inventory.getStack(slotIndex - 1);
        }

        // Return item count (0 if empty)
        return stack.isEmpty() ? 0 : stack.getCount();
    }

    /**
     * Get current player pitch angle
     */
    private Double getPlayerPitch() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[var] Player is null");
            return null;
        }

        return (double) player.getPitch();
    }

    /**
     * Get current player yaw angle
     */
    private Double getPlayerYaw() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("[var] Player is null");
            return null;
        }

        return (double) player.getYaw();
    }

    /**
     * Resolve a variable or number string to a Number value
     * Used by other aliases to support variable references
     *
     * @param input Either a variable name (e.g., "mySlot") or a number string (e.g., "5")
     * @return The resolved Number value, or null if invalid
     */
    public static Number resolveValue(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        String trimmed = input.trim();

        // First try to parse as a direct integer (preserving backward compat)
        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            // Not an integer, try double
        }

        try {
            return Double.parseDouble(trimmed);
        } catch (NumberFormatException e) {
            // Not a number, try as variable name
        }

        // Try to resolve as variable
        if (VARIABLES.containsKey(trimmed)) {
            return VARIABLES.get(trimmed);
        }

        // Not a number and not a variable
        return null;
    }

    /**
     * Resolve a variable or number string to an int value.
     * Convenience method that returns the int value of a resolved Number,
     * or null if not resolvable or not a whole-number type.
     */
    public static Integer resolveInt(String input) {
        Number n = resolveValue(input);
        return n != null ? n.intValue() : null;
    }

    /**
     * Resolve a variable or number string to a double value.
     * Convenience method that returns the double value of a resolved Number,
     * or null if not resolvable.
     */
    public static Double resolveDouble(String input) {
        Number n = resolveValue(input);
        return n != null ? n.doubleValue() : null;
    }

    /**
     * Check if a string is a valid variable reference (exists in storage)
     */
    public static boolean isVariable(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return VARIABLES.containsKey(input.trim());
    }
}
