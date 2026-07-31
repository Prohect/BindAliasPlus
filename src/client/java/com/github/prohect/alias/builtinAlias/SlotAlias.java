package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

public class SlotAlias extends BuiltinAliasWithArgs<SlotAlias> {

    public SlotAlias() {
        super("slot");
    }

    /**
     * @param args from 1-9, or a variable name that resolves to 1-9
     */
    @SuppressWarnings("DataFlowIssue")
    @Override
    public SlotAlias run(String args) {
        Integer resolved = VarAlias.resolveInt(args);

        if (resolved == null) {
            BindAliasClient.LOGGER.warn("{}[Slot]Invalid arguments: '{}' is not a valid number or variable",
                    BindAliasClient.tickPrefix(), args);
            return this;
        }

        int i = resolved;
        if (!(1 <= i && i <= 9)) {
            BindAliasClient.LOGGER.warn("{}[Slot]Invalid input! Please enter a number between 1 and 9",
                    BindAliasClient.tickPrefix());
            return this;
        }

        /*
         * KeyBinding hotbarKey = MinecraftClient.getInstance().options.hotbarKeys[i - 1]; hotbarKey.setPressed(true);
         * hotbarKey.setPressed(false); KeyBinding.onKeyPressed(hotbarKey.boundKey);
         */
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
        if (player == null) {
            BindAliasClient.LOGGER.warn("{}[Slot]Player is null", BindAliasClient.tickPrefix());
            return this;
        }
        PlayerInventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasClient.LOGGER.warn("{}[Slot]Inventory is null", BindAliasClient.tickPrefix());
            return this;
        }
        inventory.setSelectedSlot(i - 1);
        try {
            minecraftClient.getNetworkHandler().sendPacket(new UpdateSelectedSlotC2SPacket(i - 1));
        } catch (Exception e) {
            BindAliasClient.LOGGER.error("{}[Slot]Failed to update selected slot.", BindAliasClient.tickPrefix(), e);
        }
        return this;
    }
}
