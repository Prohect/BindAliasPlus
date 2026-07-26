package com.github.prohect.mixin.client;

import com.github.prohect.alias.builtinAlias.FreeCursorAlias;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * While {@code freeCursor} is active, pin the hovered slot to agent slot 14 (the player-inventory slot whose
 * {@code containerSlot} is 13) on every container screen. Vanilla recomputes {@code hoveredSlot} from the OS mouse position
 * every frame ({@code extractContents} -> {@code getHoveredSlot}); overriding that single method makes the free host cursor
 * irrelevant to hover, so {@code +drop} / swap operations deterministically target slot 14 no matter where the OS cursor rests.
 * When {@code freeCursor} is off (normal grabbed-cursor play) hover is untouched.
 */
@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    /** Agent slot 14 == player {@link Inventory} containerSlot 13 (0-based). */
    private static final int FORCED_HOVER_CONTAINER_SLOT = 13;

    @Inject(method = "getHoveredSlot", at = @At("RETURN"), cancellable = true)
    private void pinHoveredSlotTo14(double x, double y, CallbackInfoReturnable<Slot> cir) {
        if (!FreeCursorAlias.freeCursor) {
            return;
        }
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        for (Slot slot : self.getMenu().slots) {
            if (slot.getContainerSlot() == FORCED_HOVER_CONTAINER_SLOT && slot.container instanceof Inventory) {
                cir.setReturnValue(slot);
                return;
            }
        }
    }
}
