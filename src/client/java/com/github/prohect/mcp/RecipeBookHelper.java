package com.github.prohect.mcp;

import com.github.prohect.BindAliasClient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;

/**
 * Read side of the client recipe book for the MCP API: lists unlocked recipes (result-item locale name + registry id + live
 * craftability) and resolves name/id queries for the {@code applyRecipe} alias and the {@code listRecipes} tool.
 * <p>
 * Craftability mirrors the recipe book's own logic ({@code RecipeBookComponent#initVisuals}): account every player inventory
 * stack plus the crafting slots of the open {@link RecipeBookMenu}, then
 * {@link RecipeDisplayEntry#canCraft(StackedItemContents)}.
 */
public final class RecipeBookHelper {

    private RecipeBookHelper() {}

    /** One unlocked recipe, deduplicated by (item id, display name). */
    public record RecipeInfo(String name, String itemId, boolean craftable, RecipeDisplayId displayId) {}

    // ---- listing ----

    /**
     * All currently unlocked recipes, deduplicated, with live craftability. Works with or without an open screen; an open
     * {@link RecipeBookMenu} additionally accounts its crafting slots.
     */
    public static List<RecipeInfo> unlocked(Minecraft mc) {
        LocalPlayer p = mc.player;
        List<RecipeInfo> out = new ArrayList<>();
        if (p == null || p.level() == null)
            return out;
        ContextMap context = SlotDisplayContext.fromLevel(p.level());
        StackedItemContents stacked = new StackedItemContents();
        p.getInventory().fillStackedContents(stacked);
        if (p.containerMenu instanceof RecipeBookMenu menu)
            menu.fillCraftSlotsStackedContents(stacked);

        Set<String> seen = new HashSet<>();
        for (RecipeCollection collection : p.getRecipeBook().getCollections()) {
            for (RecipeDisplayEntry entry : collection.getRecipes()) {
                List<ItemStack> results = entry.resultItems(context);
                if (results.isEmpty())
                    continue;
                ItemStack result = results.get(0);
                String itemId = BuiltInRegistries.ITEM.getKey(result.getItem()).toString();
                String name = result.getHoverName().getString();
                if (!seen.add(itemId + '|' + name))
                    continue;
                out.add(new RecipeInfo(name, itemId, entry.canCraft(stacked), entry.id()));
            }
        }
        return out;
    }

    /**
     * Resolve a user query to an unlocked recipe: exact result-item id first ({@code "minecraft:torch"} or bare
     * {@code "torch"}), then case-insensitive substring of the locale name ({@code "iron sword"}).
     *
     * @return the first match, or null when nothing unlocked matches
     */
    public static RecipeInfo find(Minecraft mc, String query) {
        List<RecipeInfo> all = unlocked(mc);
        String q = query.toLowerCase(java.util.Locale.ROOT);
        for (RecipeInfo r : all)
            if (r.itemId().equals(query) || r.itemId().equals("minecraft:" + query))
                return r;
        for (RecipeInfo r : all)
            if (r.name().toLowerCase(java.util.Locale.ROOT).contains(q))
                return r;
        return null;
    }

    /** @return true when {@code query} matches this recipe (same rules as {@link #find}). */
    public static boolean matches(RecipeInfo r, String query) {
        return r.itemId().equals(query) || r.itemId().equals("minecraft:" + query)
                || r.name().toLowerCase(java.util.Locale.ROOT).contains(query.toLowerCase(java.util.Locale.ROOT));
    }

    // ---- diff bookkeeping for listRecipes (no-query mode) ----

    private static final Set<Integer> reportedDisplayIds = new HashSet<>();
    private static long baselineJoinTick = Long.MIN_VALUE;

    public static void reset() {
        reportedDisplayIds.clear();
        baselineJoinTick = Long.MIN_VALUE;
    }

    /**
     * Filter to recipes not yet reported by a previous no-query listRecipes call. Resets automatically on world change. Updates
     * the reported set as a side effect.
     */
    public static synchronized List<RecipeInfo> onlyNew(List<RecipeInfo> all) {
        if (BindAliasClient.joinTick != baselineJoinTick) {
            reportedDisplayIds.clear();
            baselineJoinTick = BindAliasClient.joinTick;
        }
        List<RecipeInfo> fresh = new ArrayList<>();
        for (RecipeInfo r : all)
            if (reportedDisplayIds.add(r.displayId().index()))
                fresh.add(r);
        return fresh;
    }

    /**
     * JSON of a recipe list: {@code [{"name":"Torch","item":"minecraft:torch","craftable":true}]}. Pre-sized for late-game
     * recipe counts (500+ unlocked recipes → ~40 KiB) to avoid repeated StringBuilder reallocations.
     */
    public static String recipesJson(List<RecipeInfo> recipes) {
        // estimate: ~80 chars per recipe entry (name + item id + craftable flag + JSON syntax)
        StringBuilder sb = new StringBuilder(recipes.size() * 80 + 2);
        sb.append('[');
        boolean first = true;
        for (RecipeInfo r : recipes) {
            if (!first)
                sb.append(',');
            first = false;
            sb.append("{\"name\":").append(GameStateCollector.jsonEscape(r.name())).append(",\"item\":")
                    .append(GameStateCollector.jsonEscape(r.itemId())).append(",\"craftable\":").append(r.craftable())
                    .append('}');
        }
        return sb.append(']').toString();
    }
}
