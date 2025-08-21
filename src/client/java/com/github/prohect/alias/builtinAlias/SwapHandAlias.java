package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SwapHandAlias extends BuiltinAliasWithoutArgs<SwapHandAlias> {
    @Override
    public SwapHandAlias run(String args) {
/*        KeyBinding key = MinecraftClient.getInstance().options.swapHandsKey;
        key.setPressed(true);
        key.setPressed(false);
        KeyBinding.onKeyPressed(key.boundKey);*/
        ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
        if (networkHandler == null) {
            BindAliasPlusClient.LOGGER.warn("[SwapHand] Network handler is null");
            return this;
        }
        networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
        return this;
    }
}
