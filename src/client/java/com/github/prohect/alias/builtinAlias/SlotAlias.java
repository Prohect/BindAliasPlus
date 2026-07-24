package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.world.entity.player.Inventory;

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
            BindAliasPlusClient.LOGGER.warn("{}[Slot]Invalid arguments: '{}' is not a valid number or variable",
                    BindAliasPlusClient.tickPrefix(), args);
            return this;
        }

        int i = resolved;
        if (!(1 <= i && i <= 9)) {
            BindAliasPlusClient.LOGGER.warn("{}[Slot]Invalid input! Please enter a number between 1 and 9",
                    BindAliasPlusClient.tickPrefix());
            return this;
        }

        /*
         * KeyMapping hotbarKey = Minecraft.getInstance().options.hotbarKeys[i - 1]; hotbarKey.setDown(true);
         * hotbarKey.setDown(false); KeyMapping.click(hotbarKey.key);
         */
        Minecraft minecraftClient = Minecraft.getInstance();
        LocalPlayer player = minecraftClient.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[Slot]Player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        Inventory inventory = player.getInventory();
        if (inventory == null) {
            BindAliasPlusClient.LOGGER.warn("{}[Slot]Inventory is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        inventory.setSelectedSlot(i - 1);
        try {
            minecraftClient.getConnection().send(new ServerboundSetCarriedItemPacket(i - 1));
        } catch (Exception e) {
            BindAliasPlusClient.LOGGER.error("{}[Slot]Failed to update selected slot.", BindAliasPlusClient.tickPrefix(), e);
        }
        return this;
    }
}
