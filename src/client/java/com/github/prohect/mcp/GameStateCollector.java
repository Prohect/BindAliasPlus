package com.github.prohect.mcp;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.AliasWithArgs;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import com.github.prohect.util.McScreenHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Builds the canonical game-state snapshot for the MCP envelope. Every field maps to something visible on the vanilla HUD (or
 * the currently open screen): hearts/hunger/armor bars, XP bar, crosshair target outline, potion icons, subtitles (see
 * {@link SoundCapture}), locator bar, hotbar, and container screens.
 * <p>
 * {@link #collect()} returns an insertion-ordered map of field name → raw JSON value text; {@link StateTracker} diffs
 * consecutive snapshots member-wise. All values are formatted here (2dp positions, 1dp angles/distances) so the diff does not
 * fire on invisible float noise.
 */
public final class GameStateCollector {

    private GameStateCollector() {}

    /** Builtin boolean alias name → user-facing name as documented in the MCP tool instructions. */
    private static final Map<String, String> HELD_KEY_NAMES;

    static {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("builtinAttack", "+attack");
        m.put("builtinUse", "+use");
        m.put("builtinForward", "+forward");
        m.put("builtinBack", "+back");
        m.put("builtinLeft", "+left");
        m.put("builtinRight", "+right");
        m.put("builtinJump", "+jump");
        m.put("builtinSneak", "+sneak");
        m.put("builtinSprint", "+sprint");
        m.put("builtinDrop", "+drop");
        m.put("builtinScreenshot", "+screenshot");
        m.put("builtinPlayerList", "+playerList");
        m.put("builtinFreeCursor", "+freeCursor");
        m.put("builtinEsc", "esc");
        m.put("builtinAdvancements", "+advancements");
        m.put("builtinDebugOverlay", "+debugOverlay");
        m.put("builtinOpenInventory", "+openInventory");
        m.put("builtinSilent", "+silent");
        HELD_KEY_NAMES = Collections.unmodifiableMap(m);
    }

    /** Canonical ordered snapshot. Call on the Minecraft main thread. */
    public static LinkedHashMap<String, String> collect() {
        LinkedHashMap<String, String> out = new LinkedHashMap<>();
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer p = mc.player;

        String worldName = worldName(mc);
        if (worldName != null)
            out.put("world_name", jsonEscape(worldName));
        if (p != null)
            out.put("dimension", jsonEscape(p.level().dimension().identifier().toString()));

        var screen = McScreenHelper.getCurrentScreen(mc);
        out.put("screen", screen == null ? "null" : jsonEscape(screen.getClass().getSimpleName()));

        if (p == null)
            return out;

        out.put("pos", posJson(p));
        out.put("health", fmt1(p.getHealth()));
        if (p.getAbsorptionAmount() > 0)
            out.put("absorption", fmt1(p.getAbsorptionAmount()));
        out.put("hunger", String.valueOf(p.getFoodData().getFoodLevel()));
        out.put("saturation", fmt1(p.getFoodData().getSaturationLevel()));
        out.put("armor", String.valueOf(p.getArmorValue()));
        out.put("xp", "{\"level\":" + p.experienceLevel + ",\"percent\":" + Math.round(p.experienceProgress * 100.0F) + '}');

        Collection<MobEffectInstance> effects = p.getActiveEffects();
        if (!effects.isEmpty())
            out.put("effects", effectsJson(effects));

        String target = targetJson(mc, p);
        if (target != null)
            out.put("target", target);

        String players = playersJson(mc, p);
        if (players != null)
            out.put("players", players);

        String heldKeys = heldKeysJson();
        if (heldKeys != null)
            out.put("held_keys", heldKeys);

        ItemStack held = p.getMainHandItem();
        if (held != null && !held.isEmpty()) {
            out.put("held_item", jsonEscape(BuiltInRegistries.ITEM.getKey(held.getItem()).toString()));
            out.put("held_item_count", String.valueOf(held.getCount()));
        } else {
            out.put("held_item", "null");
            out.put("held_item_count", "0");
        }

        int selectedSlot = p.getInventory().getSelectedSlot();
        out.put("selected_hotbar_slot", String.valueOf(selectedSlot + 1));
        ItemStack selectedItem = p.getInventory().getItem(selectedSlot);
        if (selectedItem.isDamageableItem())
            out.put("durability", "{\"remaining\":" + (selectedItem.getMaxDamage() - selectedItem.getDamageValue())
                    + ",\"max\":" + selectedItem.getMaxDamage() + '}');

        String[] hotbar = hotbarJson(p);
        out.put("hotbar", hotbar[0]);
        if (hotbar[1] != null)
            out.put("hotbar_empty", jsonEscape(hotbar[1]));

        return out;
    }

    // ---- fields ----

    private static String worldName(Minecraft mc) {
        try {
            IntegratedServer server = mc.getSingleplayerServer();
            if (server != null)
                return server.getWorldData().getLevelName();
            ServerData current = mc.getCurrentServer();
            if (current != null)
                return current.name != null && !current.name.isBlank() ? current.name : current.ip;
        } catch (Exception ignored) {
        }
        return null;
    }

    private static String posJson(LocalPlayer p) {
        String feet;
        if (p.isInLava())
            feet = "in_lava";
        else if (p.isInWater())
            feet = "in_water";
        else if (p.isInPowderSnow)
            feet = "in_powder_snow";
        else if (p.isFallFlying())
            feet = "fall_flying";
        else if (p.onGround())
            feet = "on_ground";
        else
            feet = "midair";
        return "{\"x\":" + fmt2(p.getX()) + ",\"y\":" + fmt2(p.getY()) + ",\"z\":" + fmt2(p.getZ()) + ",\"yaw\":"
                + fmt1(p.getYRot()) + ",\"pitch\":" + fmt1(p.getXRot()) + ",\"feet\":\"" + feet + "\"}";
    }

    private static String effectsJson(Collection<MobEffectInstance> effects) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (MobEffectInstance inst : effects) {
            if (!first)
                sb.append(',');
            first = false;
            StringBuilder e = new StringBuilder(I18n.get(inst.getDescriptionId()));
            if (inst.getAmplifier() > 0)
                e.append(' ').append(I18n.get("enchantment.level." + (inst.getAmplifier() + 1)));
            e.append(" (").append(formatDuration(inst.getDuration())).append(')');
            sb.append(jsonEscape(e.toString()));
        }
        return sb.append(']').toString();
    }

    /** ticks → "m:ss" (or "inf" for infinite). */
    private static String formatDuration(int ticks) {
        if (ticks < 0)
            return "inf";
        int seconds = ticks / 20;
        return (seconds / 60) + ":" + String.format(Locale.ROOT, "%02d", seconds % 60);
    }

    private static String targetJson(Minecraft mc, LocalPlayer p) {
        HitResult hit = mc.hitResult;
        if (hit == null || hit.getType() == HitResult.Type.MISS)
            return null;
        double distance = hit.getLocation().distanceTo(p.getEyePosition());
        if (hit instanceof BlockHitResult blockHit) {
            BlockState state = p.level().getBlockState(blockHit.getBlockPos());
            return "{\"kind\":\"block\",\"name\":" + jsonEscape(state.getBlock().getName().getString()) + ",\"distance\":"
                    + fmt1(distance) + '}';
        }
        if (hit instanceof EntityHitResult entityHit) {
            Entity e = entityHit.getEntity();
            String kind = e instanceof Player ? "player" : "entity";
            return "{\"kind\":\"" + kind + "\",\"name\":" + jsonEscape(e.getName().getString()) + ",\"distance\":"
                    + fmt1(distance) + '}';
        }
        return null;
    }

    /** Locator-bar style list of other players: {@code "Steve [yaw+40 pitch-0 12.5m]"}, nearest first. */
    private static String playersJson(Minecraft mc, LocalPlayer p) {
        if (mc.level == null)
            return null;
        List<? extends Player> others = new ArrayList<>(mc.level.players());
        others.removeIf(other -> other == p || other.isRemoved());
        if (others.isEmpty())
            return null;
        others.sort(Comparator.comparingDouble(p::distanceToSqr));
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Player other : others) {
            if (!first)
                sb.append(',');
            first = false;
            sb.append(jsonEscape(other.getName().getString() + " ["
                    + SoundCapture.directionOf(p, other.getX() - p.getX(), other.getY() - p.getY(), other.getZ() - p.getZ())
                    + "]"));
        }
        return sb.append(']').toString();
    }

    /** Currently held builtin boolean aliases, by their user-facing names ({@code "+attack"} ...). Null when none. */
    private static String heldKeysJson() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Map.Entry<String, String> e : HELD_KEY_NAMES.entrySet()) {
            AliasWithArgs<?> alias = Alias.aliasesWithArgs_notSuggested.get(e.getKey());
            if (alias == null)
                alias = Alias.aliasesWithArgs.get(e.getKey());
            if (alias instanceof BuiltinAliasWithBooleanArgs<?> b && b.flag) {
                if (!first)
                    sb.append(',');
                first = false;
                sb.append(jsonEscape(e.getValue()));
            }
        }
        return first ? null : sb.append(']').toString();
    }

    /**
     * Occupied hotbar slots as {@code [{"slot":N,"name":"..","count":N}]} (1-based). Empty slots are reported separately via
     * the optional {@code hotbar_empty} field (range-compressed, see {@link #compressRanges}) — an all-empty hotbar is just
     * {@code "hotbar":[], "hotbar_empty":"1-9"} instead of nine nulls.
     *
     * @return {@code [hotbarArrayJson, emptyRangesOrNull]}
     */
    private static String[] hotbarJson(LocalPlayer p) {
        StringBuilder sb = new StringBuilder("[");
        List<Integer> empty = new ArrayList<>();
        boolean first = true;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = p.getInventory().getItem(i);
            if (stack.isEmpty()) {
                empty.add(i + 1);
                continue;
            }
            if (!first)
                sb.append(',');
            first = false;
            sb.append("{\"slot\":").append(i + 1).append(",\"name\":").append(jsonEscape(stack.getHoverName().getString()))
                    .append(",\"count\":").append(stack.getCount()).append('}');
        }
        return new String[] {sb.append(']').toString(), empty.isEmpty() ? null : compressRanges(empty)};
    }

    /** Compress sorted-or-unsorted slot numbers to ranges: {@code [1,2,3,5,7,8,9] → "1-3 5 7-9"}. */
    private static String compressRanges(List<Integer> slots) {
        Collections.sort(slots);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < slots.size(); i++) {
            int start = slots.get(i);
            int end = start;
            while (i + 1 < slots.size() && slots.get(i + 1) == end + 1)
                end = slots.get(++i);
            if (out.length() > 0)
                out.append(' ');
            out.append(start);
            if (end > start)
                out.append('-').append(end);
        }
        return out.toString();
    }

    // ---- container ----

    /**
     * Point-in-time view of an open container menu, structured for slot-level diffing (see {@link StateTracker}):
     * {@link #items} maps the slot's swapSlot index (raw JSON — {@code 1}..{@code 41} for player inventory, {@code "c1"}.. for
     * container slots) to its full entry JSON; {@link #emptyInv} is the range-compressed empty-player-slot list; {@link #grid}
     * is the container_grid array JSON (null when the menu has no container slots).
     */
    static final class ContainerSnapshot {
        final int menuIdentity;
        final Map<String, String> items;
        final String emptyInv;
        final String grid;

        ContainerSnapshot(int menuIdentity, Map<String, String> items, String emptyInv, String grid) {
            this.menuIdentity = menuIdentity;
            this.items = items;
            this.emptyInv = emptyInv;
            this.grid = grid;
        }
    }

    /** Snapshot the currently open container menu, or null when no container screen is open. Main thread only. */
    static ContainerSnapshot containerSnapshot(Minecraft mc, LocalPlayer p) {
        var screen = McScreenHelper.getCurrentScreen(mc);
        if (!(screen instanceof AbstractContainerScreen<?> containerScreen))
            return null;
        AbstractContainerMenu menu = containerScreen.getMenu();

        Map<String, String> items = new LinkedHashMap<>();
        List<Integer> emptyInv = new ArrayList<>();
        List<int[]> gridSlots = new ArrayList<>(); // {c, x, y, occupied(0|1)}

        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i);
            int c = i + 1;
            ItemStack stack = slot.getItem();
            boolean isPlayerInv = slot.container instanceof Inventory;

            if (!stack.isEmpty()) {
                String indexJson = isPlayerInv ? String.valueOf(slot.getContainerSlot() + 1) : jsonEscape("c" + c);
                StringBuilder entry = new StringBuilder("{\"index\":").append(indexJson);
                entry.append(",\"item\":").append(jsonEscape(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString()));
                entry.append(",\"name\":").append(jsonEscape(stack.getHoverName().getString()));
                entry.append(",\"count\":").append(stack.getCount());
                if (stack.isDamageableItem())
                    entry.append(",\"durability\":{\"remaining\":").append(stack.getMaxDamage() - stack.getDamageValue())
                            .append(",\"max\":").append(stack.getMaxDamage()).append('}');
                if (stack.isEnchanted())
                    entry.append(",\"enchanted\":true");
                appendTooltipIfValuable(mc, p, stack, entry);
                items.put(indexJson, entry.append('}').toString());
            }

            if (isPlayerInv) {
                if (stack.isEmpty())
                    emptyInv.add(slot.getContainerSlot() + 1);
            } else {
                gridSlots.add(new int[] {c, slot.x, slot.y, stack.isEmpty() ? 0 : 1});
            }
        }

        return new ContainerSnapshot(System.identityHashCode(menu), items, compressRanges(emptyInv), gridJson(gridSlots));
    }

    /**
     * Full container JSON: {@code {"inventory_items":[...], "empty_inv":"...", "container_grid":[...]}} — sent on getState, on
     * open, and whenever the menu instance changes.
     */
    static String containerFullJson(ContainerSnapshot snap) {
        StringBuilder out = new StringBuilder("{\"inventory_items\":[");
        boolean first = true;
        for (String entry : snap.items.values()) {
            if (!first)
                out.append(',');
            first = false;
            out.append(entry);
        }
        out.append("],\"empty_inv\":").append(jsonEscape(snap.emptyInv));
        if (snap.grid != null)
            out.append(",\"container_grid\":").append(snap.grid);
        return out.append('}').toString();
    }

    /**
     * Slot-level diff between two snapshots of the SAME menu: only slots whose stack changed are listed (a slot that became
     * empty appears as {@code {"index":K,"item":null}}); {@code empty_inv} / {@code container_grid} are included only when they
     * changed.
     *
     * @return the partial container JSON, or null when nothing changed
     */
    static String containerDiffJson(ContainerSnapshot prev, ContainerSnapshot cur) {
        StringBuilder items = new StringBuilder();
        for (Map.Entry<String, String> e : cur.items.entrySet()) {
            if (!e.getValue().equals(prev.items.get(e.getKey()))) {
                if (items.length() > 0)
                    items.append(',');
                items.append(e.getValue());
            }
        }
        for (String indexJson : prev.items.keySet()) {
            if (!cur.items.containsKey(indexJson)) {
                if (items.length() > 0)
                    items.append(',');
                items.append("{\"index\":").append(indexJson).append(",\"item\":null}");
            }
        }

        StringBuilder out = new StringBuilder();
        if (items.length() > 0)
            out.append("\"inventory_items\":[").append(items).append(']');
        if (!cur.emptyInv.equals(prev.emptyInv)) {
            if (out.length() > 0)
                out.append(',');
            out.append("\"empty_inv\":").append(jsonEscape(cur.emptyInv));
        }
        if (!java.util.Objects.equals(cur.grid, prev.grid)) {
            if (out.length() > 0)
                out.append(',');
            out.append("\"container_grid\":").append(cur.grid == null ? "null" : cur.grid);
        }
        return out.length() == 0 ? null : out.insert(0, '{').append('}').toString();
    }

    /**
     * container_grid array JSON: one row string per grid row (every row ends with {@code \n}). Runs of adjacent container cells
     * share one {@code |group|} — {@code cNN:*} (occupied), {@code cNN:o} (empty); blank cells are plain padding. Width is
     * {@code cols*6+1} so slot columns line up across rows.
     *
     * @param gridSlots {@code {cIndex, x, y, occupied(0|1)}} per non-player-inventory slot
     * @return the JSON array text, or null when the menu has no container slots
     */
    private static String gridJson(List<int[]> gridSlots) {
        if (gridSlots.isEmpty())
            return null;
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] s : gridSlots) {
            minX = Math.min(minX, s[1]);
            maxX = Math.max(maxX, s[1]);
            minY = Math.min(minY, s[2]);
            maxY = Math.max(maxY, s[2]);
        }

        int cols = (maxX - minX) / 18 + 1;
        int rows = (maxY - minY) / 18 + 1;
        String[][] grid = new String[rows][cols];
        for (int r = 0; r < rows; r++)
            Arrays.fill(grid[r], "     ");

        for (int[] s : gridSlots) {
            int col = (s[1] - minX) / 18;
            int row = (s[2] - minY) / 18;
            char state = s[3] == 0 ? 'o' : '*';
            grid[row][col] = "c" + String.format(Locale.ROOT, "%02d", s[0]) + ':' + state;
        }

        StringBuilder out = new StringBuilder("[");
        int width = cols * 6 + 1;
        for (int r = 0; r < rows; r++) {
            if (r > 0)
                out.append(',');
            out.append(jsonEscape(buildGridRow(grid[r], width)));
        }
        return out.append(']').toString();
    }

    /**
     * One grid row: runs of adjacent container cells are wrapped in {@code |...|} groups separated by a space; blank cells
     * outside runs are plain padding. Always ends with {@code \n}.
     */
    private static String buildGridRow(String[] rowCells, int width) {
        int last = -1;
        for (int c = rowCells.length - 1; c >= 0; c--)
            if (!rowCells[c].startsWith(" ")) {
                last = c;
                break;
            }
        StringBuilder row = new StringBuilder();
        boolean inGroup = false;
        for (int c = 0; c <= last; c++) {
            if (rowCells[c].startsWith(" ")) {
                if (inGroup) {
                    row.append('|');
                    inGroup = false;
                }
                row.append("      "); // 6 spaces per blank cell keeps columns aligned
            } else {
                if (!inGroup) {
                    row.append('|');
                    inGroup = true;
                } else {
                    row.append(' ');
                }
                row.append(rowCells[c]);
            }
        }
        if (inGroup)
            row.append('|');
        while (row.length() < width)
            row.append(' ');
        return row.append('\n').toString();
    }

    /** Vanilla NORMAL (non-advanced) tooltip, included only when it shows more than the plain name line. */
    private static void appendTooltipIfValuable(Minecraft mc, LocalPlayer p, ItemStack stack, StringBuilder items) {
        try {
            List<net.minecraft.network.chat.Component> lines =
                    stack.getTooltipLines(Item.TooltipContext.of(p.level()), p, TooltipFlag.NORMAL);
            if (lines.size() <= 1)
                return;
            items.append(",\"tooltip\":[");
            boolean first = true;
            for (var line : lines) {
                if (!first)
                    items.append(',');
                first = false;
                items.append(jsonEscape(line.getString()));
            }
            items.append(']');
        } catch (Exception ignored) {
        }
    }

    // ---- shared json helpers (same package) ----

    /** Minimal JSON string escaping (no external dependency). */
    static String jsonEscape(String s) {
        if (s == null)
            return "null";
        StringBuilder sb = new StringBuilder("\"");
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.append('"').toString();
    }

    static String fmt1(double v) {
        return String.format(Locale.ROOT, "%.1f", v);
    }

    static String fmt2(double v) {
        return String.format(Locale.ROOT, "%.2f", v);
    }
}
