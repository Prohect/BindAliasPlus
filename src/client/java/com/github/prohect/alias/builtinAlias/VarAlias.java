package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

/**
 * VarAlias - Store and retrieve in-game variables
 *
 * Usage: - var\varName\source - Store a value from a source into varName
 *
 * Sources: - "hotbarSlot" or "selectedSlot" - Current hotbar slot (1-9) - "itemsOfSlot0" to "itemsOfSlot9" - Item count in slot
 * (0=offhand, 1-9=hotbar) - "pitch" - Player's current pitch angle (float) - "yaw" - Player's current yaw angle (float) - "cN"
 * (e.g., c1, c5, c12) - Container slot number for swapSlot compatibility, stores the number N - A number (e.g., 5, 3.14) -
 * Direct integer or float value
 *
 * Examples: - var\mySlot\hotbarSlot - Store current hotbar slot - var\backup\5 - Store the number 5 - var\myPitch\pitch - Store
 * current pitch angle - var\myYaw\yaw - Store current yaw angle - var\arrowCount\itemsOfSlot2 - Store item count from hotbar
 * slot 2 - var\resultSlot\c3 - Store container slot 3 for use with swapSlot
 */
public class VarAlias extends BuiltinAliasWithArgs<VarAlias> {

    public VarAlias() {
        super("var");
    }

    // Global variable storage
    public static final Map<String, Number> GENERAL_VARIABLES = new HashMap<>();

    // Map variable name -> container slot number (1-based), set via cN source (e.g. var\mySlot\c5).
    // Only SwapSlotAlias reads this to distinguish container slot references from plain player slot numbers.
    public static final Map<String, Integer> CONTAINER_SLOT_VARIABLES = new HashMap<>();

    // Track which variables were loaded from config file
    public static final java.util.Set<String> CFG_VARIABLES = new java.util.HashSet<>();
    public static final java.util.Set<String> CFG_CONTAINER_SLOT_VARIABLES = new java.util.HashSet<>();

    // Reserved sentinel returned by fromContainerSlotSource when parsing fails.
    // Chosen to be hard to guess and impossible to collide with a real slot index.
    private static final int CONTAINER_SLOT_PARSE_ERR = Integer.MIN_VALUE + 17;

    // Pattern to check if variable name starts with a number (not allowed)
    private static final Pattern STARTS_WITH_NUMBER = Pattern.compile("^[0-9].*");

    @Override
    public VarAlias run(String args) {
        ArrayList<String> argsList = Alias.getDefinitionSplits(args);

        if (argsList.size() < 2) {
            BindAliasPlusClient.LOGGER.error(
                    "{}[var] Invalid arguments: expected varName and source. Usage: var\\varName\\source",
                    BindAliasPlusClient.tickPrefix());
            return this;
        }

        String varName = argsList.get(0).trim();
        String source = argsList.get(1).trim();

        // Validate variable name
        if (!isValidVarName(varName)) {
            BindAliasPlusClient.LOGGER.error("{}[var] Invalid variable name '{}': variable names cannot start with a number",
                    BindAliasPlusClient.tickPrefix(), varName);
            return this;
        }

        // Get value from source
        Number value = getValueFromSource(source);
        if (value == null) {
            BindAliasPlusClient.LOGGER.error("{}[var] Failed to get value from source '{}'", BindAliasPlusClient.tickPrefix(),
                    source);
            return this;
        }

        // Store variable
        GENERAL_VARIABLES.put(varName, value);

        // Track container slot references (cN source) for swapSlot compatibility
        int cSlot = fromContainerSlotSource(source);
        if (cSlot != CONTAINER_SLOT_PARSE_ERR) {
            CONTAINER_SLOT_VARIABLES.put(varName, cSlot);
        } else {
            CONTAINER_SLOT_VARIABLES.remove(varName);
        }

        BindAliasPlusClient.LOGGER.info("{}[var] Variable '{}' set to {}", BindAliasPlusClient.tickPrefix(), varName, value);

        return this;
    }

    /**
     * Run the var alias with autoload tracking
     */
    public VarAlias run(String args, boolean fromAutoload) {
        ArrayList<String> argsList = Alias.getDefinitionSplits(args);

        if (argsList.size() < 2) {
            BindAliasPlusClient.LOGGER.error(
                    "{}[var] Invalid arguments: expected varName and source. Usage: var\\varName\\source",
                    BindAliasPlusClient.tickPrefix());
            return this;
        }

        String varName = argsList.get(0).trim();
        String source = argsList.get(1).trim();

        // Validate variable name
        if (!isValidVarName(varName)) {
            BindAliasPlusClient.LOGGER.error("{}[var] Invalid variable name '{}': variable names cannot start with a number",
                    BindAliasPlusClient.tickPrefix(), varName);
            return this;
        }

        // Get value from source
        Number value = getValueFromSource(source);
        if (value == null) {
            BindAliasPlusClient.LOGGER.error("{}[var] Failed to get value from source '{}'", BindAliasPlusClient.tickPrefix(),
                    source);
            return this;
        }

        // Store variable

        // Track container slot references (cN source) for swapSlot compatibility
        int cSlot = fromContainerSlotSource(source);
        if (cSlot != CONTAINER_SLOT_PARSE_ERR) {
            CONTAINER_SLOT_VARIABLES.put(varName, cSlot);
            if (fromAutoload)
                CFG_CONTAINER_SLOT_VARIABLES.add(varName);
        } else {
            GENERAL_VARIABLES.put(varName, value);
            if (fromAutoload)
                CFG_VARIABLES.add(varName);

        }

        // Track if from autoload


        BindAliasPlusClient.LOGGER.info("{}[var] Variable '{}' set to {}", BindAliasPlusClient.tickPrefix(), varName, value);

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
     * Parse a cN container-slot source string (lowercase 'c' + digits, e.g. c1, c5, c12).
     *
     * @return the 1-based slot number (>= 1), or {@link #CONTAINER_SLOT_PARSE_ERR} if not a valid cN string
     */
    private static int fromContainerSlotSource(String source) {
        String s = source.trim();
        if (s.length() < 2 || s.charAt(0) != 'c')
            return CONTAINER_SLOT_PARSE_ERR;
        try {
            int n = Integer.parseInt(s.substring(1));
            return n >= 1 ? n : CONTAINER_SLOT_PARSE_ERR;
        } catch (NumberFormatException e) {
            return CONTAINER_SLOT_PARSE_ERR;
        }
    }

    /**
     * Get numeric value from various sources Returns Integer for integral sources, Double for floating-point sources
     */
    private Number getValueFromSource(String source) {
        // Check if source is a game variable reference
        if ("hotbarSlot".equalsIgnoreCase(source) || "selectedSlot".equalsIgnoreCase(source)) {
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

        // Check for cN pattern (container slot reference, e.g. c1, c5, c12).
        int cSlot = fromContainerSlotSource(source);
        if (cSlot != CONTAINER_SLOT_PARSE_ERR) {
            return cSlot;
        }

        // Try to parse as a number: prefer integer if possible, otherwise double
        try {
            return Integer.parseInt(source);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(source);
            } catch (NumberFormatException e2) {
                BindAliasPlusClient.LOGGER.error(
                        "{}[var] Unknown source '{}' - expected 'hotbarSlot', 'selectedSlot', 'itemsOfSlot0-9', 'pitch', 'yaw', 'cN', or a number",
                        BindAliasPlusClient.tickPrefix(), source);
                return null;
            }
        }
    }

    /**
     * Get current hotbar slot (1-9 format to match mod conventions)
     */
    private Integer getCurrentHotbarSlot() {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[var] Player is null", BindAliasPlusClient.tickPrefix());
            return null;
        }

        Inventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasPlusClient.LOGGER.warn("{}[var] Inventory is null", BindAliasPlusClient.tickPrefix());
            return null;
        }

        // getSelectedSlot returns 0-8, we add 1 to match the mod's 1-9 convention
        return inventory.getSelectedSlot() + 1;
    }

    /**
     * Get item count from a slot using "itemsOfSlot0-9" pattern 0 = offhand, 1-9 = hotbar slots
     */
    private Integer getItemCountFromSlot(String source) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[var] Player is null", BindAliasPlusClient.tickPrefix());
            return null;
        }

        Inventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasPlusClient.LOGGER.warn("{}[var] Inventory is null", BindAliasPlusClient.tickPrefix());
            return null;
        }

        // Extract slot number from "itemsOfSlotN"
        String slotStr = source.substring("itemsOfSlot".length());
        int slotIndex;
        try {
            slotIndex = Integer.parseInt(slotStr);
        } catch (NumberFormatException e) {
            BindAliasPlusClient.LOGGER.error("{}[var] Invalid slot number in '{}' - expected itemsOfSlot0 to itemsOfSlot9",
                    BindAliasPlusClient.tickPrefix(), source);
            return null;
        }

        // Validate slot range (0 for offhand, 1-9 for hotbar)
        if (slotIndex < 0 || slotIndex > 9) {
            BindAliasPlusClient.LOGGER.error("{}[var] Slot number out of range in '{}' - must be 0-9 (0=offhand, 1-9=hotbar)",
                    BindAliasPlusClient.tickPrefix(), source);
            return null;
        }

        // Get the item stack
        // Slot mapping: 0=offhand (internal slot 40), 1-9=hotbar (internal slots 0-8)
        ItemStack stack;
        if (slotIndex == 0) {
            // Offhand slot (internal inventory index 40)
            stack = inventory.getItem(40);
        } else {
            // Hotbar slots (1-9 maps to inventory index 0-8)
            stack = inventory.getItem(slotIndex - 1);
        }

        // Return item count (0 if empty)
        return stack.isEmpty() ? 0 : stack.getCount();
    }

    /**
     * Resolve a variable or number string to a Number value Used by other aliases to support variable references
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
        if (GENERAL_VARIABLES.containsKey(trimmed)) {
            return GENERAL_VARIABLES.get(trimmed);
        }

        // Not a number and not a variable
        return null;
    }

    /**
     * Resolve a variable or number string to an int value. Convenience method that returns the int value of a resolved Number,
     * or null if not resolvable or not a whole-number type.
     */
    public static Integer resolveInt(String input) {
        Number n = resolveValue(input);
        return n != null ? n.intValue() : null;
    }

    /**
     * Resolve a variable or number string to a double value. Convenience method that returns the double value of a resolved
     * Number, or null if not resolvable.
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
        return GENERAL_VARIABLES.containsKey(input.trim());
    }

    /**
     * Get current player pitch angle
     */
    private Double getPlayerPitch() {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[var] Player is null", BindAliasPlusClient.tickPrefix());
            return null;
        }

        return (double) player.getXRot();
    }

    /**
     * Get current player yaw angle
     */
    private Double getPlayerYaw() {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;

        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[var] Player is null", BindAliasPlusClient.tickPrefix());
            return null;
        }

        return (double) player.getYRot();
    }
}
