package com.github.prohect.mixin.client;

import net.minecraft.world.tick.TickManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Relaxes the {@code TickManager} rate clamp from {@code max(rate, 1.0F)} to {@code max(rate, 0.1F)}, matching the widened
 * {@code /tick rate} argument range from {@link TickCommandMixin} — otherwise rates below 1.0 would silently be clamped back to
 * 1.0 by {@code setTickRate}.
 */
@Mixin(TickManager.class)
public class TickRateManagerMixin {

    @Redirect(method = "setTickRate", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private static float bindAliasMinTickRate(float a, float b) {
        return Math.max(a, 0.1F);
    }
}
