package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs;
import com.github.prohect.mcp.RecipeBookHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.RecipeBookMenu;

/**
 * {@code applyRecipe\<query>} — place an unlocked, craftable recipe into the crafting grid of the open recipe menu (player
 * inventory, crafting table, furnace, ...), exactly like clicking it in the recipe book. No crafting is performed — taking the
 * result is a separate action (e.g. {@code swapSlot\c1\10}).
 * <p>
 * The query is a result-item registry id ({@code minecraft:torch} or bare {@code torch}) or a case-insensitive substring of the
 * locale name ({@code "iron sword"}). Errors (no recipe menu open / not unlocked / missing ingredients) go to the local game
 * chat; success is logged to the mod channel.
 */
public class ApplyRecipeAlias extends BuiltinAliasWithGreedyStringArgs<ApplyRecipeAlias> {

    public ApplyRecipeAlias() {
        super("applyRecipe");
    }

    @Override
    public ApplyRecipeAlias run(String args) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) {
            BindAliasPlusClient.LOGGER.warn("{}[applyRecipe]player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        String query = args == null ? "" : args.trim();
        if (query.isEmpty()) {
            chatError(player, "[applyRecipe] usage: applyRecipe\\<recipe name or item id>");
            return this;
        }
        if (!(player.containerMenu instanceof RecipeBookMenu menu)) {
            chatError(player, "[applyRecipe] no recipe menu open (open the inventory or a crafting station)");
            return this;
        }
        RecipeBookHelper.RecipeInfo recipe = RecipeBookHelper.find(mc, query);
        if (recipe == null) {
            chatError(player, "[applyRecipe] not unlocked or unknown recipe: " + query);
            return this;
        }
        if (!recipe.craftable()) {
            chatError(player, "[applyRecipe] missing ingredients for: " + recipe.name());
            return this;
        }
        mc.gameMode.handlePlaceRecipe(menu.containerId, recipe.displayId(), false);
        BindAliasPlusClient.LOGGER.info("{}[applyRecipe] applied {}", BindAliasPlusClient.tickPrefix(), recipe.name());
        return this;
    }

    /** Errors go to the local game chat (visible on the HUD and in the chat channel), per spec. */
    private static void chatError(LocalPlayer player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }
}
