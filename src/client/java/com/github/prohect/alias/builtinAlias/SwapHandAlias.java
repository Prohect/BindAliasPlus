package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;

public class SwapHandAlias extends BuiltinAliasWithoutArgs<SwapHandAlias> {

    public SwapHandAlias() {
        super("swapHand");
    }

    @Override
    public SwapHandAlias run(String args) {
        /*
         * KeyMapping key = Minecraft.getInstance().options.keySwapOffhand; key.setDown(true); key.setDown(false);
         * KeyMapping.click(key.key);
         */
        if (Alias.isUnderTextInputScreen())
            return this;
        ClientPacketListener networkHandler = Minecraft.getInstance().getConnection();
        if (networkHandler == null) {
            BindAliasPlusClient.LOGGER.warn("{}[SwapHand] Network handler is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        networkHandler.send(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND,
                BlockPos.ZERO, Direction.DOWN));
        return this;
    }
}
