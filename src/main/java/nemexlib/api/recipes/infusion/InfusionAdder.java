package nemexlib.api.recipes.infusion;

import nemexlib.api.thaumcraft.aspects.Aspects;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.InfusionRecipe;

public class InfusionAdder {

    private InfusionAdder(){}

    /**
     * Adds a new infusion recipe linked to a research
     * @param tag Research tag
     * @param instability Instability of the infusion recipe
     * @param aspects Essentia
     * @param output Result
     * @param input Catalyst
     * @param ingredients The items on the pedestals
     * @return The recipe generated
     */
    public static InfusionRecipe addInfusion(String tag, int instability, Aspects aspects, ItemStack output, ItemStack input, ItemStack ... ingredients) {
        if (tag == null || aspects == null || output == null || input == null || ingredients == null || ingredients.length == 0) throw new ParameterIsNullOrEmpty();
        return ThaumcraftApi.addInfusionCraftingRecipe(tag, output, Math.max(instability, 1), aspects, input, ingredients);
    }
}
