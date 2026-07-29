package com.github.prohect.mcp;

import com.github.prohect.BindAliasPlusClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEventListener;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.network.chat.Component;

/**
 * Feeds the {@link GameChannels#SOUND} channel. Registered as a {@link SoundEventListener} on the client {@code SoundManager} —
 * the same hook the vanilla subtitle overlay uses, so exactly the sounds that would show a HUD caption are reported.
 * <p>
 * Message format: {@code "[tick:<tick>] <subtitle> [yaw<±N> pitch<±N> <dist>m]"}, e.g.
 * {@code "[tick:123] Zombie groans [yaw-40 pitch+20 4.2m]"} — the tick uses the same clock as the envelope's {@code "tick"}
 * field; the direction is the yaw/pitch <b>relative to the listener's view at the moment the sound was heard</b>, rounded to
 * 20° steps (turning {@code yaw\<relYaw>} faces the source). Sounds at the listener's own position collapse to
 * {@code here <dist>m}. Repeats of the same sound coalesce (see {@link GameChannels#postCoalescing}).
 */
public final class SoundCapture implements SoundEventListener {

    private static final SoundCapture INSTANCE = new SoundCapture();

    private SoundCapture() {}

    /** Register on the client sound manager. Safe to call multiple times (deduped by listener identity). */
    public static void register() {
        Minecraft.getInstance().getSoundManager().addListener(INSTANCE);
    }

    @Override
    public void onPlaySound(SoundInstance sound, WeighedSoundEvents soundEvent, float range) {
        try {
            Component subtitle = soundEvent.getSubtitle();
            if (subtitle == null)
                return;
            LocalPlayer p = Minecraft.getInstance().player;
            if (p == null)
                return;
            String name = subtitle.getString();
            double dx = sound.getX() - p.getX();
            double dy = sound.getY() - p.getY();
            double dz = sound.getZ() - p.getZ();
            // mirror SubtitleOverlay.isAudibleFrom: skip sounds the player cannot actually hear
            double distSq = dx * dx + dy * dy + dz * dz;
            if (!Float.isInfinite(range) && distSq > (double) range * range)
                return;
            GameChannels.postCoalescing(GameChannels.SOUND, name,
                    tickPrefix() + name + " [" + directionOf(p, dx, dy, dz) + "]");
        } catch (Exception ignored) {
            // never break the sound engine
        }
    }

    /** {@code "[tick:N] "} matching the envelope's {@code "tick":N} term; empty when not in a world. */
    static String tickPrefix() {
        return BindAliasPlusClient.joinTick < 0 ? ""
                : "[tick:" + (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick) + "] ";
    }

    /** Direction quantization step (degrees) — coarse on purpose, the ear is not a protractor. */
    private static final double DEG_STEP = 20.0;

    /**
     * 3D direction formatter shared by the sound channel and the players list: {@code "yaw+20 pitch-20 4.2m"} — yaw/pitch of
     * the target <b>relative to the player's current view</b>, each rounded to the nearest {@value #DEG_STEP}° step, plus the
     * 3D distance. At point-blank (&lt;0.5 m) the angles are meaningless and collapse to {@code "here <dist>m"}.
     *
     * @param dx target minus player position
     */
    static String directionOf(LocalPlayer p, double dx, double dy, double dz) {
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        double horiz = Math.sqrt(dx * dx + dz * dz);
        if (dist < 0.31)
            return "here " + fmt1(dist) + "m";
        StringBuilder sb = new StringBuilder();
        if (horiz >= 0.5) {
            // MC yaw convention: 0=south(+Z), 90=west(-X), ±180=north(-Z), -90=east(+X)
            double targetYaw = Math.toDegrees(Math.atan2(-dx, dz));
            sb.append("yaw").append(signed(clamp(normalize180(targetYaw - p.getYRot())))).append(' ');
        }
        // MC pitch convention: -90=up, 0=horizon, 90=down
        double targetPitch = -Math.toDegrees(Math.atan2(dy, horiz));
        sb.append("pitch").append(signed(clamp(normalize180(targetPitch - p.getXRot()))));
        return sb.append(' ').append(fmt1(dist)).append('m').toString();
    }

    /** Round to the nearest {@value #DEG_STEP}° step (custom precision clamp). */
    private static int clamp(double deg) {
        return (int) (Math.round(deg / DEG_STEP) * (long) DEG_STEP);
    }

    private static double normalize180(double deg) {
        deg %= 360.0;
        if (deg > 180.0)
            deg -= 360.0;
        else if (deg < -180.0)
            deg += 360.0;
        return deg;
    }

    /** Always-signed int: 4 → "+4", -4 → "-4", 0 → "+0". */
    private static String signed(int v) {
        return (v >= 0 ? "+" : "") + v;
    }

    private static String fmt1(double v) {
        return String.format(java.util.Locale.ROOT, "%.1f", v);
    }
}
