package com.github.prohect.mixin.client;

import com.github.prohect.alias.builtinAlias.FreeCursorAlias;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * While {@code freeCursor} is active, pin the hovered slot to agent slot 14 (the player-inventory slot whose
 * {@code index} is 13) on every container screen. Vanilla recomputes {@code focusedSlot} from the OS mouse position
 * every frame ({@code renderMain} -&gt; {@code getSlotAt}); overriding that single method makes the free host cursor
 * irrelevant to hover, so {@code +drop} / swap operations deterministically target slot 14 no matter where the OS
 * cursor rests.
 * When {@code freeCursor} is off (normal grabbed-cursor play) hover is untouched.
 */
@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {

    /** Agent slot 14 == player inventory index 13 (0-based). */
    private static final int FORCED_HOVER_INDEX = 13;

    @Inject(method = "getSlotAt", at = @At("RETURN"), cancellable = true)
    private void pinFocusedSlotTo14(double mouseX, double mouseY, CallbackInfoReturnable<Slot> cir) {
        if (!FreeCursorAlias.freeCursor) {
            return;
        }
        HandledScreen<?> self = (HandledScreen<?>) (Object) this;
        for (Slot slot : self.handler.slots) {
            if (slot.index == FORCED_HOVER_INDEX && slot.inventory instanceof PlayerInventory) {
                cir.setReturnValue(slot);
                return;
            }
        }
    }
}
