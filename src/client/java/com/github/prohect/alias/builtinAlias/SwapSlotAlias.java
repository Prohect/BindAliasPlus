package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class SwapSlotAlias extends BuiltinAliasWithArgs {

    /**
     * @param args pattern: slot1 slot2, spilt by white space,
     *             0 means the second hand,
     *             1-9 means hotbarSlots,
     *             10-36 means slots inside inventory,
     *             37-40 means equipments, 37 is feet, 40 means head
     */
    @Override
    public void run(String args) {
        String[] strings = args.split("\\\\");
        int[] slots = new int[2];
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (strings.length != 2) {
            if (strings.length == 1) {
                slots[0] = Integer.parseInt(strings[0]);
                assert minecraftClient.player != null;
                slots[1] = minecraftClient.player.getInventory().getSelectedSlot();
            } else {
                BindAliasPlusClient.LOGGER.warn("[SwitchSlot]Invalid arguments:args pattern not expected");
                return;
            }
        } else {
            slots[0] = Integer.parseInt(strings[0]);
            slots[1] = Integer.parseInt(strings[1]);
        }
        try {
            if (slots[0] < 0 || slots[1] < 0 || slots[0] > 40 || slots[1] > 40 || slots[0] == slots[1]) {
                BindAliasPlusClient.LOGGER.warn("[SwitchSlot]Invalid arguments: slot index out of bounds");
                return;
            }

            ClientPlayNetworkHandler networkHandler = minecraftClient.getNetworkHandler();

            if (networkHandler == null) {
                BindAliasPlusClient.LOGGER.warn("[SwitchSlot]network handler is null");
                return;
            }

            ClientPlayerEntity player = minecraftClient.player;
            if (player == null) {
                BindAliasPlusClient.LOGGER.warn("[switchSlot]Player is null");
                return;
            }
            PlayerInventory inventory = player.getInventory();
            if (inventory == null) {
                BindAliasPlusClient.LOGGER.warn("[switchSlot]Inventory is null");
                return;
            }

            int selectedSlot = player.getInventory().getSelectedSlot();
            Screen currentScreen = minecraftClient.currentScreen;
            boolean creativeInventory = currentScreen instanceof CreativeInventoryScreen;
            boolean inInventory = currentScreen instanceof InventoryScreen || creativeInventory;
            if (creativeInventory) {
                currentScreen.close();
            }
            InventoryScreen inventoryScreen = inInventory ? creativeInventory ? new InventoryScreen(player) : (InventoryScreen) currentScreen : new InventoryScreen(player);
            if (!inInventory) minecraftClient.setScreen(inventoryScreen);
            if (creativeInventory) minecraftClient.setScreen(inventoryScreen);
            try {
                if (slots[0] <= 9 && slots[1] <= 9) {
                    if (slots[0] == 0) {
                        networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(slots[1] - 1));
                        networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                    } else if (slots[1] == 0) {
                        networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(slots[0] - 1));
                        networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                    } else {
                        networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(slots[0] - 1));
                        networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                        networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(slots[1] - 1));
                        networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                        networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(slots[0] - 1));
                        networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                    }
                } else {
                    ClientPlayerInteractionManager interactionManager = minecraftClient.interactionManager;
                    if (slots[0] == 0) {
                        interactionManager.clickSlot(inventoryScreen.handler.syncId, getSlot(inventoryScreen, slots[1] - 1).id, 40, SlotActionType.SWAP, player);
                    } else if (slots[1] == 0) {
                        interactionManager.clickSlot(inventoryScreen.handler.syncId, getSlot(inventoryScreen, slots[0] - 1).id, 40, SlotActionType.SWAP, player);
                    } else {
                        interactionManager.clickSlot(inventoryScreen.handler.syncId, getSlot(inventoryScreen, slots[0] - 1).id, 40, SlotActionType.SWAP, player);
                        interactionManager.clickSlot(inventoryScreen.handler.syncId, getSlot(inventoryScreen, slots[1] - 1).id, 40, SlotActionType.SWAP, player);
                        interactionManager.clickSlot(inventoryScreen.handler.syncId, getSlot(inventoryScreen, slots[0] - 1).id, 40, SlotActionType.SWAP, player);
                    }
                }
                networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(selectedSlot - 1));
            } catch (Exception e) {
                BindAliasPlusClient.LOGGER.error("[SwitchSlot]Failed to update selected slot.", e);
            } finally {
                if (!inInventory) inventoryScreen.close();
            }

        } catch (NumberFormatException e) {
            BindAliasPlusClient.LOGGER.warn("[SwitchSlot]Invalid arguments: cant parse number");
        }
    }

    private static @Nullable Slot getSlot(InventoryScreen inventoryScreen, int index) {
        for (Slot slot : inventoryScreen.handler.slots) {
            if (slot.getIndex() == index && slot.inventory instanceof PlayerInventory) {
                return slot;
            }
        }
        return null;
    }
}
