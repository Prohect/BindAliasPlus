package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import com.github.prohect.util.McScreenHelper;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;

public class SwapSlotAlias extends BuiltinAliasWithArgs<SwapSlotAlias> {

    public SwapSlotAlias() {
        super("swapSlot");
    }

  /**
	 * A parsed slot argument: either a player inventory slot (containerSlot inside
	 * {@link Inventory}, 0-40) or a container slot (index into
	 * {@link AbstractContainerMenu#slots} of the currently open menu).
	 */
	private record SlotRef(boolean container, int index) {
		static SlotRef player(int index) {
			return new SlotRef(false, index);
		}

		static SlotRef container(int index) {
			return new SlotRef(true, index);
		}
	}

    /**
     * @param args args typed by user. pattern: slot1 slot2, or slot1, spilt by white space, 1-9 means hotbarSlots, 10-36 means
     *        slots inside inventory, 37-40 means equipments, 37 is feet, 40 is head 41 means the second hand, cN (e.g. c1, c5)
     *        means the Nth slot (1-based) of the currently open container menu - works in any containerScreen (chest, crafting
     *        table, furnace, anvil, enchanting table, smithing table, grindstone, loom, stonecutter, merchant, ...), making it
     *        possible to craft/forge/enchant etc. The menu order usually lists container slots first and player inventory slots
     *        last, e.g. crafting table: c1 result, c2-c10 grid; furnace: c1 input, c2 fuel, c3 output; anvil: c1 left, c2
     *        right, c3 result. Also supports variable names (e.g., mySlot) created with var alias
     */
    @Override
    public SwapSlotAlias run(String args) {
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[switchSlot]Player is null", BindAliasClient.tickPrefix());
            return this;
        }
        Inventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasClient.LOGGER.warn("{}[switchSlot]Inventory is null", BindAliasClient.tickPrefix());
            return this;
        }
        int selectedSlot = inventory.getSelectedSlot();
        ClientPacketListener networkHandler = minecraftClient.getConnection();
        if (networkHandler == null) {
            BindAliasClient.LOGGER.warn("{}[SwitchSlot]network handler is null", BindAliasClient.tickPrefix());
            return this;
        }

        String[] strings = args.split(Pattern.quote(String.valueOf(Alias.divider4AliasArgs)));

        SlotRef[] slots;
        if (strings.length == 1) {
            slots = new SlotRef[] {parseSlotRef(strings[0]), SlotRef.player(selectedSlot),};
        } else if (strings.length == 2) {
            slots = new SlotRef[] {parseSlotRef(strings[0]), parseSlotRef(strings[1]),};
        } else {
            BindAliasClient.LOGGER.warn("{}[SwitchSlot]Invalid arguments:args pattern not expected",
                    BindAliasClient.tickPrefix());
            return this;
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                BindAliasClient.LOGGER.warn("{}[SwitchSlot]Invalid arguments: '{}' is not a valid slot (1-41, cN, or variable)",
                        BindAliasClient.tickPrefix(), strings[i]);
                return this;
            }
        }

        if (slots[0].equals(slots[1])) {
            BindAliasClient.LOGGER.warn("{}[SwitchSlot]Invalid arguments: slot index1 equals to slot index2",
                    BindAliasClient.tickPrefix());
            return this;
        }

        Screen currentScreen = Alias.getCurrentScreen();
        boolean creativeInventory = Alias.isInCreativeInventoryScreen();
        boolean inInventory = Alias.isInInventoryScreen() || creativeInventory;
        AbstractContainerScreen<?> containerScreen =
                currentScreen instanceof AbstractContainerScreen<?> acs && !inInventory ? acs : null;
        if (creativeInventory)
            currentScreen.onClose();

        try {
            final int offhand = 40;
            boolean bothPlain = !slots[0].container() && !slots[1].container();
            boolean slot0IsOffhand = bothPlain && slots[0].index() == offhand;
            boolean hasOffHand = bothPlain && (slots[1].index() == offhand || slot0IsOffhand);
            boolean slot0IsHotbar = bothPlain && slots[0].index() < 9;
            boolean insideHotbarsAndOffHand =
                    bothPlain && (slot0IsHotbar || slot0IsOffhand) && (slots[1].index() < 9 || slots[1].index() == offhand);

            if (insideHotbarsAndOffHand) {
                // packet-based swaps touch only the player inventory,
                // so they work even while another containerScreen is open
                if (hasOffHand) {
                    swapSlotOffhand(networkHandler, slot0IsOffhand ? slots[1].index() : slots[0].index());
                } else {
                    swapSlotOffhand(networkHandler, slots[0].index());
                    swapSlotOffhand(networkHandler, slots[1].index());
                    swapSlotOffhand(networkHandler, slots[0].index());
                }
                networkHandler.send(new ServerboundSetCarriedItemPacket(selectedSlot));
                return this;
            }

            // avoid close previous screen, which might cause unexpected behavior
            if (Alias.isUnderAnyScreen() && !inInventory && containerScreen == null)
                return this;

            AbstractContainerMenu menu;
            InventoryScreen inventoryScreen = null;
            if (containerScreen != null) {
                // any open containerScreen: chest, crafting table, furnace,
                // anvil, enchanting table, etc. Use its menu directly.
                menu = containerScreen.getMenu();
            } else {
                // the inventory screen will be opened/closed automatically in following codes
                inventoryScreen =
                        inInventory ? creativeInventory ? new InventoryScreen(player) : (InventoryScreen) currentScreen
                                : new InventoryScreen(player);
                if (!inInventory || creativeInventory)
                    McScreenHelper.setScreen(minecraftClient, inventoryScreen);
                menu = inventoryScreen.getMenu();
            }
            try {
                MultiPlayerGameMode interactionManager = minecraftClient.gameMode;
                if (interactionManager != null) {
                    Slot slot0 = resolveSlot(menu, slots[0]);
                    Slot slot1 = resolveSlot(menu, slots[1]);
                    if (slot0 != null) {
                        if (slot1 != null) {
                            swapInMenu(interactionManager, menu, slot0, slot1, player);
                        } else
                            BindAliasClient.LOGGER.warn("{}[SwitchSlot]slot1 {} not found in current menu",
                                    BindAliasClient.tickPrefix(), strings.length == 2 ? strings[1] : "");
                    } else
                        BindAliasClient.LOGGER.warn("{}[SwitchSlot]slot0 {} not found in current menu",
                                BindAliasClient.tickPrefix(), strings[0]);
                } else
                    BindAliasClient.LOGGER.warn("{}[SwitchSlot]interactionManager is null", BindAliasClient.tickPrefix());
            } finally {
                if (inventoryScreen != null && !inInventory)
                    inventoryScreen.onClose();
            }
        } catch (Exception e) {
            BindAliasClient.LOGGER.error("{}[SwitchSlot]Failed to swap slots with args {}.", BindAliasClient.tickPrefix(), args,
                    e);
        }

        return this;
    }

    /**
     * Parse one slot argument: "cN" for a container menu slot (1-based index into the open menu's slot list), or a plain number
     * / variable (1-41) for a player inventory slot.
     *
     * <p>
     * Variables created from a cN source (e.g. var\mySlot\c5) are tracked in {@link VarAlias#CONTAINER_SLOT_VARIABLES} so they
     * resolve as container slots here.
     *
     * @return the parsed SlotRef, or null if invalid
     */
    private static SlotRef parseSlotRef(String arg) {
        String trimmed = arg.trim();

        // cN is always a direct container slot reference, even if a variable with
        // the same name exists ("c<n>" could also be a valid var name per VarAlias).
        if (trimmed.length() > 1 && trimmed.charAt(0) == 'c') {
            try {
                int n = Integer.parseInt(trimmed.substring(1));
                if (n >= 1)
                    return SlotRef.container(n - 1);
            } catch (NumberFormatException ignored) {
            }
        }

        // Check if this is a variable that holds a container slot reference
        Integer cSlot = VarAlias.CONTAINER_SLOT_VARIABLES.get(trimmed);
        if (cSlot != null) {
            return SlotRef.container(cSlot - 1);
        }

        Integer resolved = VarAlias.resolveInt(trimmed);
        if (resolved == null)
            return null;
        int index = resolved - 1;
        return index >= 0 && index <= 40 ? SlotRef.player(index) : null;
    }

    /**
     * Find the Slot object for a SlotRef inside the given menu. Plain player slots match by containerSlot inside the player
     * {@link Inventory}; container slots index directly into the menu's slot list.
     */
    private static Slot resolveSlot(AbstractContainerMenu menu, SlotRef ref) {
        if (ref.container()) {
            return ref.index() >= 0 && ref.index() < menu.slots.size() ? menu.slots.get(ref.index()) : null;
        }
        for (Slot slot : menu.slots) {
            if (slot.getContainerSlot() == ref.index() && slot.container instanceof Inventory) {
                return slot;
            }
        }
        return null;
    }

    /**
     * The SWAP-click button for a slot, if it is directly swap-addressable: 0-8 for hotbar slots, 40 for the offhand, -1
     * otherwise. A SWAP click with this button on any other slot swaps the two (works in any menu).
     */
    private static int swapButton(Slot slot) {
        if (!(slot.container instanceof Inventory))
            return -1;
        int containerSlot = slot.getContainerSlot();
        if (containerSlot >= 0 && containerSlot < 9)
            return containerSlot;
        if (containerSlot == 40)
            return 40;
        return -1;
    }

    /**
     * Swap the contents of two slots inside an open menu, whatever they hold. If one slot is hotbar/offhand-addressable, a
     * single SWAP click does it. Otherwise a guarded PICKUP sequence (pick up A, click B, put back into A) is used; take-only
     * slots (crafting/anvil/furnace results) then behave as "take the result into B", since they reject the put-back click.
     *
     * <p>
     * <b>SWAP path limitation:</b> Vanilla's {@code SWAP} click is all-or-nothing — if the hotbar/offhand item cannot be placed
     * into the container slot (e.g. non-fuel into a furnace fuel slot, any item into a result slot), the server silently
     * rejects the entire swap and neither item moves. For taking items from restricted slots, use an empty hotbar slot or swap
     * with a non-hotbar inventory slot (10–36) to fall through to the PICKUP path which handles the rejection gracefully.
     */
    private static void swapInMenu(MultiPlayerGameMode interactionManager, AbstractContainerMenu menu, Slot slot0, Slot slot1,
            LocalPlayer player) {
        int button0 = swapButton(slot0);
        if (button0 != -1) {
            clickSlot(interactionManager, menu, slot1, button0, ContainerInput.SWAP, player);
            return;
        }
        int button1 = swapButton(slot1);
        if (button1 != -1) {
            clickSlot(interactionManager, menu, slot0, button1, ContainerInput.SWAP, player);
            return;
        }
        clickSlot(interactionManager, menu, slot0, 0, ContainerInput.PICKUP, player);
        clickSlot(interactionManager, menu, slot1, 0, ContainerInput.PICKUP, player);
        if (!menu.getCarried().isEmpty()) {
            clickSlot(interactionManager, menu, slot0, 0, ContainerInput.PICKUP, player);
        }
        if (!menu.getCarried().isEmpty()) {
            // slot0 rejected the put-back (take-only slot): restore slot1
            clickSlot(interactionManager, menu, slot1, 0, ContainerInput.PICKUP, player);
        }
        if (!menu.getCarried().isEmpty()) {
            BindAliasClient.LOGGER.warn("{}[switchSlot]An item stack remains on the cursor; click any slot to place it",
                    BindAliasClient.tickPrefix());
        }
    }

    /**
     * @param slot the slot of an inventory of a screen, chest inventory or player inventory for example
     * @param button index of a list, could be 0,1,...,8 which means hotbars, or 40 which means hasOffHand, would be used to get
     *        a certain slot object via playerInventory.getItem(button)
     *        <p>
     *        value range check inside, only 0-8 and 40 allowed
     */
    private static void clickSlot(MultiPlayerGameMode interactionManager, AbstractContainerMenu menu, Slot slot, int button,
            ContainerInput input, LocalPlayer player) {
        interactionManager.handleContainerInput(menu.containerId, slot.index, button, input, player);
    }

    private static void swapSlotOffhand(ClientPacketListener networkHandler, int ratherOffhand) {
        networkHandler.send(new ServerboundSetCarriedItemPacket(ratherOffhand));
        networkHandler.send(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND,
                BlockPos.ZERO, Direction.DOWN));
    }
}
