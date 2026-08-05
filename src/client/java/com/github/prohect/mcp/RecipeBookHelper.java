package com.github.prohect.mcp;

import com.github.prohect.BindAliasClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.NetworkRecipeId;
import net.minecraft.recipe.RecipeDisplayEntry;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.display.FurnaceRecipeDisplay;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.ShapedCraftingRecipeDisplay;
import net.minecraft.recipe.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.recipe.display.SlotDisplayContexts;
import net.minecraft.registry.Registries;
import net.minecraft.screen.AbstractCraftingScreenHandler;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.util.context.ContextParameterMap;

/**
 * Read side of the client recipe book for the MCP API: lists unlocked recipes (result-item locale name + registry id + live
 * craftability) and resolves name/id queries for the {@code applyRecipe} alias and the {@code listRecipes} tool.
 * <p>
 * Craftability mirrors the recipe book's own logic ({@code RecipeBookComponent#initVisuals}): account every player inventory
 * stack plus the crafting slots of the open {@link AbstractRecipeScreenHandler}, then
 * {@link RecipeDisplayEntry#isCraftable(RecipeFinder)}.
 */
public final class RecipeBookHelper {

    private RecipeBookHelper() {}

    /** One unlocked recipe, deduplicated by (item id, display name), preferring a display the open menu can place. */
    public record RecipeInfo(String name, String itemId, boolean craftable, boolean placeable, NetworkRecipeId displayId) {}

    // ---- listing ----

    /**
     * All currently unlocked recipes, deduplicated, with live craftability. Works with or without an open screen; an open
     * {@link AbstractRecipeScreenHandler} additionally accounts its crafting slots.
     */
    public static List<RecipeInfo> unlocked(MinecraftClient mc) {
        ClientPlayerEntity p = mc.player;
        List<RecipeInfo> out = new ArrayList<>();
        if (p == null || p.getEntityWorld() == null)
            return out;
        ContextParameterMap context = SlotDisplayContexts.createParameters(p.getEntityWorld());
        RecipeFinder stacked = new RecipeFinder();
        p.getInventory().populateRecipeFinder(stacked);
        AbstractRecipeScreenHandler menu = p.currentScreenHandler instanceof AbstractRecipeScreenHandler m ? m : null;
        if (menu != null)
            menu.populateRecipeFinder(stacked);

        // Several displays can yield the same result item (e.g. cobblestone slab: crafting display AND stonecutter
        // display). Dedup prefers the display the open menu can place — otherwise applyRecipe could pick one the
        // server rejects (ClassCastException in recipeMatches) or silently ignores.
        Map<String, Integer> indexByKey = new HashMap<>();
        for (RecipeResultCollection collection : p.getRecipeBook().getOrderedResults()) {
            for (RecipeDisplayEntry entry : collection.getAllRecipes()) {
                List<ItemStack> results = entry.getStacks(context);
                if (results.isEmpty())
                    continue;
                ItemStack result = results.get(0);
                String itemId = Registries.ITEM.getId(result.getItem()).toString();
                String name = result.getName().getString();
                boolean placeable = menu != null && placeableIn(menu, entry.display());
                RecipeInfo info = new RecipeInfo(name, itemId, entry.isCraftable(stacked), placeable, entry.id());
                String key = itemId + '|' + name;
                Integer existing = indexByKey.get(key);
                if (existing == null) {
                    indexByKey.put(key, out.size());
                    out.add(info);
                } else if (placeable && !out.get(existing).placeable()) {
                    out.set(existing, info);
                }
            }
        }
        return out;
    }

    /**
     * Whether the open menu can place this display — mirrors the recipe book's own per-menu filtering
     * ({@code CraftingRecipeBookComponent#canDisplay}, {@code FurnaceRecipeBookComponent#selectMatchingRecipes}): crafting
     * menus (inventory 2x2, crafting table 3x3) take crafting displays that fit the grid, furnace menus take furnace displays,
     * and everything else is rejected (the server throws on such packets or silently ignores them).
     */
    public static boolean placeableIn(AbstractRecipeScreenHandler menu, RecipeDisplay display) {
        if (menu instanceof AbstractCraftingScreenHandler crafting) {
            return switch (display) {
                case ShapedCraftingRecipeDisplay shaped ->
                    crafting.getWidth() >= shaped.width() && crafting.getHeight() >= shaped.height();
                case ShapelessCraftingRecipeDisplay shapeless ->
                    crafting.getWidth() * crafting.getHeight() >= shapeless.ingredients().size();
                default -> false;
            };
        }
        if (menu instanceof AbstractFurnaceScreenHandler)
            return display instanceof FurnaceRecipeDisplay;
        return false;
    }

    /**
     * Resolve a user query to an unlocked recipe: exact result-item id first ({@code "minecraft:torch"} or bare
     * {@code "torch"}), then case-insensitive substring of the locale name ({@code "iron sword"}).
     *
     * @return the first match, or null when nothing unlocked matches
     */
    public static RecipeInfo find(MinecraftClient mc, String query) {
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
                    .append(",\"placeable\":").append(r.placeable()).append('}');
        }
        return sb.append(']').toString();
    }
}
