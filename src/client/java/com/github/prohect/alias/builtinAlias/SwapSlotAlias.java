package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;

public class SwapSlotAlias extends BuiltinAliasWithArgs<SwapSlotAlias> {

    public SwapSlotAlias() {
        super("swapSlot");
    }

    /**
     * @param args args typed by user.
     *             pattern: slot1 slot2, or slot1, spilt by white space,
     *             1-9 means hotbarSlots,
     *             10-36 means slots inside inventory,
     *             37-40 means equipments, 37 is feet, 40 is head
     *             41 means the second hand,
     *             Also supports variable names (e.g., mySlot) created with var alias
     */
    @Override
    public SwapSlotAlias run(String args) {
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[switchSlot]Player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        Inventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasPlusClient.LOGGER.warn("{}[switchSlot]Inventory is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        int selectedSlot = inventory.getSelectedSlot();
        ClientPacketListener networkHandler = minecraftClient.getConnection();
        if (networkHandler == null) {
            BindAliasPlusClient.LOGGER.warn(
                "{}[SwitchSlot]network handler is null",
                BindAliasPlusClient.tickPrefix()
            );
            return this;
        }

        String[] strings = args.split(
            Pattern.quote(String.valueOf(Alias.divider4AliasArgs))
        );
        int[] slots = new int[] { 0, selectedSlot };

        if (strings.length == 1) {
            Integer resolvedSlot = VarAlias.resolveInt(strings[0]);
            if (resolvedSlot == null) {
                BindAliasPlusClient.LOGGER.warn(
                    "{}[SwitchSlot]Invalid arguments: '{}' is not a valid number or variable",
                    BindAliasPlusClient.tickPrefix(),
                    strings[0]
                );
                return this;
            }
            slots[0] = resolvedSlot - 1;
        } else if (strings.length == 2) {
            Integer resolvedSlot0 = VarAlias.resolveInt(strings[0]);
            Integer resolvedSlot1 = VarAlias.resolveInt(strings[1]);

            if (resolvedSlot0 == null) {
                BindAliasPlusClient.LOGGER.warn(
                    "{}[SwitchSlot]Invalid arguments: '{}' is not a valid number or variable",
                    BindAliasPlusClient.tickPrefix(),
                    strings[0]
                );
                return this;
            }
            if (resolvedSlot1 == null) {
                BindAliasPlusClient.LOGGER.warn(
                    "{}[SwitchSlot]Invalid arguments: '{}' is not a valid number or variable",
                    BindAliasPlusClient.tickPrefix(),
                    strings[1]
                );
                return this;
            }

            slots[0] = resolvedSlot0 - 1;
            slots[1] = resolvedSlot1 - 1;
        } else {
            BindAliasPlusClient.LOGGER.warn(
                "{}[SwitchSlot]Invalid arguments:args pattern not expected",
                BindAliasPlusClient.tickPrefix()
            );
            return this;
        }

        if (
            slots[0] < 0 ||
            slots[1] < 0 ||
            slots[0] > 40 ||
            slots[1] > 40 ||
            slots[0] == slots[1]
        ) {
            BindAliasPlusClient.LOGGER.warn(
                "{}[SwitchSlot]Invalid arguments: slot index out of bounds, or slot index1 equals to slot index2",
                BindAliasPlusClient.tickPrefix()
            );
            return this;
        }

        Screen currentScreen = Alias.getCurrentScreen();
        boolean creativeInventory = Alias.isInCreativeInventoryScreen();
        boolean inInventory = Alias.isInInventoryScreen() || creativeInventory;
        if (creativeInventory) currentScreen.onClose();

        try {
            final int offhand = 40;
            boolean slot0IsOffhand = slots[0] == offhand;
            boolean hasOffHand = slots[1] == offhand || slot0IsOffhand;
            int ratherOffhand = slot0IsOffhand ? slots[1] : slots[0];

            boolean slot0IsHotbar = slots[0] < 9;
            boolean hasHotbar = slots[1] < 9 || slot0IsHotbar;
            int hotbar = slot0IsHotbar ? slots[0] : slots[1];
            int ratherHotbar = slot0IsHotbar ? slots[1] : slots[0];

            boolean insideHotbarsAndOffHand =
                (slot0IsHotbar || slot0IsOffhand) &&
                (slots[1] < 9 || slots[1] == offhand);
            if (insideHotbarsAndOffHand) {
                if (hasOffHand) {
                    swapSlotOffhand(networkHandler, ratherOffhand);
                } else {
                    swapSlotOffhand(networkHandler, slots[0]);
                    swapSlotOffhand(networkHandler, slots[1]);
                    swapSlotOffhand(networkHandler, slots[0]);
                }
                networkHandler.send(
                    new ServerboundSetCarriedItemPacket(selectedSlot)
                );
            } else {
                // avoid close previous screen, which might cause unexpected behavior
                if (Alias.isUnderAnyScreen() && !inInventory) return this;
                // the inventory screen will be opened/closed automatically in following codes
                InventoryScreen inventoryScreen = inInventory
                    ? creativeInventory
                        ? new InventoryScreen(player)
                        : (InventoryScreen) currentScreen
                    : new InventoryScreen(player);
                if (!inInventory) minecraftClient.setScreen(inventoryScreen);
                if (creativeInventory) minecraftClient.setScreen(
                    inventoryScreen
                );
                try {
                    MultiPlayerGameMode interactionManager =
                        minecraftClient.gameMode;
                    if (interactionManager != null) {
                        if (hasOffHand) {
                            Slot slotRatherOffhand = getSlot(
                                inventoryScreen,
                                ratherOffhand
                            );
                            if (slotRatherOffhand != null) clickSlot(
                                interactionManager,
                                inventoryScreen,
                                slotRatherOffhand,
                                offhand,
                                player
                            );
                            else BindAliasPlusClient.LOGGER.warn(
                                "{}[switchSlot]Slot {} is null",
                                BindAliasPlusClient.tickPrefix(),
                                ratherOffhand
                            );
                        } else if (hasHotbar) {
                            Slot slotRatherHotbar = getSlot(
                                inventoryScreen,
                                ratherHotbar
                            );
                            if (slotRatherHotbar != null) clickSlot(
                                interactionManager,
                                inventoryScreen,
                                slotRatherHotbar,
                                hotbar,
                                player
                            );
                            else BindAliasPlusClient.LOGGER.warn(
                                "{}[switchSlot]Slot {} is nul",
                                BindAliasPlusClient.tickPrefix(),
                                ratherHotbar
                            );
                        } else {
                            Slot slot0 = getSlot(inventoryScreen, slots[0]);
                            Slot slot1 = getSlot(inventoryScreen, slots[1]);
                            if (slot0 != null) {
                                if (slot1 != null) {
                                    clickSlot(
                                        interactionManager,
                                        inventoryScreen,
                                        slot0,
                                        offhand,
                                        player
                                    );
                                    clickSlot(
                                        interactionManager,
                                        inventoryScreen,
                                        slot1,
                                        offhand,
                                        player
                                    );
                                    clickSlot(
                                        interactionManager,
                                        inventoryScreen,
                                        slot0,
                                        offhand,
                                        player
                                    );
                                } else BindAliasPlusClient.LOGGER.warn(
                                    "{}[SwitchSlot]slot1 {} is null",
                                    BindAliasPlusClient.tickPrefix(),
                                    slots[1]
                                );
                            } else BindAliasPlusClient.LOGGER.warn(
                                "{}[SwitchSlot]slot0 {} is null",
                                BindAliasPlusClient.tickPrefix(),
                                slots[0]
                            );
                        }
                    } else BindAliasPlusClient.LOGGER.warn(
                        "{}[SwitchSlot]interactionManager is null",
                        BindAliasPlusClient.tickPrefix()
                    );
                } finally {
                    if (!inInventory) inventoryScreen.onClose();
                }
            }
        } catch (Exception e) {
            BindAliasPlusClient.LOGGER.error(
                "{}[SwitchSlot]Failed to swap slots.",
                BindAliasPlusClient.tickPrefix(),
                e
            );
        }

        return this;
    }

    /**
     * @param slot   the slot of an inventory of a screen, chest inventory or player inventory for example
     * @param button index of a list, could be 0,1,...,8 which means hotbars, or 40 which means hasOffHand, would be used to get a certain slot object via playerInventory.getItem(button)
     *               <p>value range check inside, only 0-8 and 40 allowed
     */
    private static void clickSlot(
        MultiPlayerGameMode interactionManager,
        InventoryScreen inventoryScreen,
        Slot slot,
        int button,
        LocalPlayer player
    ) {
        interactionManager.handleContainerInput(
            inventoryScreen.menu.containerId,
            slot.index,
            button,
            ContainerInput.SWAP,
            player
        );
    }

    private static void swapSlotOffhand(
        ClientPacketListener networkHandler,
        int ratherOffhand
    ) {
        networkHandler.send(new ServerboundSetCarriedItemPacket(ratherOffhand));
        networkHandler.send(
            new ServerboundPlayerActionPacket(
                ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND,
                BlockPos.ZERO,
                Direction.DOWN
            )
        );
    }

    private static Slot getSlot(InventoryScreen inventoryScreen, int index) {
        for (Slot slot : inventoryScreen.menu.slots) {
            if (
                slot.getContainerSlot() == index &&
                slot.container instanceof Inventory
            ) {
                return slot;
            }
        }
        return null;
    }
}
