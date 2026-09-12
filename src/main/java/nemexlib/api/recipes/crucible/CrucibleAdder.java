package nemexlib.api.recipes.crucible;

import net.minecraft.item.ItemStack;
import nemexlib.api.items.DeepCopy;
import nemexlib.api.thaumcraft.aspects.Aspects;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleAdder {

    private CrucibleAdder(){}

    protected static CrucibleRecipe addRecipe(final String tag, final Aspects aspects, final ItemStack output, final Object input) {
        if (tag == null || aspects == null || output == null || input == null) throw new ParameterIsNullOrEmpty();
        return ThaumcraftApi.addCrucibleRecipe(tag, output, input, aspects);
    }

    /**
     * Adds a single crucible recipe
     * @param tag Research's tag
     * @param aspects Crucible aspects needed
     * @param output The output ItemStack
     * @param input The recipe catalyst
     * @return The recipe generated
     */
    public static CrucibleRecipe addRecipe(final String tag, final Aspects aspects, final ItemStack output, final ItemStack input) {
        return addRecipe(tag, aspects, output, (Object) input);
    }

    /**
     * Adds multiple crucible recipe
     * <p>Will follow this pattern : input[0] -> output[0],  input[1] -> output[1] ...</p>
     * @param tag Research's tag
     * @param aspects Crucible aspects needed
     * @param output The output ItemStacks
     * @param input The recipe catalysts
     * @return The recipe generated
     */
    public static CrucibleRecipe[] addMultipleRecipe(final String tag, final Aspects aspects, final ItemStack[] output, final ItemStack ... input) {
        if (output.length != input.length || output.length == 0) throw new ParameterIsNullOrEmpty();
        CrucibleRecipe[] recipes = new CrucibleRecipe[output.length];
        for (int i = 0; i < output.length; i++)
            recipes[i] = addRecipe(tag, aspects, output[i], input[i]);
        return recipes;
    }

    /**
     * Adds multiple crucible recipe with editable output stacksize
     * <p>Will follow this pattern : input[0] -> output[0],  input[1] -> output[1] ...</p>
     * @param tag Research's tag
     * @param aspects Crucible aspects needed
     * @param output The output ItemStacks
     * @param nb The new output stacksize
     * @param input The recipe catalysts
     * @return The recipe generated
     */
    public static CrucibleRecipe[] addMultipleRecipe(final String tag, final Aspects aspects, final ItemStack[] output, final int nb, final ItemStack ... input) {
        return addMultipleRecipe(tag, aspects, DeepCopy.i(output, nb), input);
    }

    /**
     * Adds multiple crucible recipe that all output the same ItemStack
     * <p>Will follow this pattern : input[0] -> output,  input[1] -> output ...</p>
     * @param tag Research's tag
     * @param aspects Crucible aspects needed
     * @param output The output ItemStack
     * @param input The recipe catalyst
     * @return The recipe generated
     */
    public static CrucibleRecipe[] addMultipleSingleRecipe(final String tag, final Aspects aspects, final ItemStack output, final ItemStack ... input) {
        if (input.length == 0) throw new ParameterIsNullOrEmpty();
        CrucibleRecipe[] recipes = new CrucibleRecipe[input.length];
        for (int i = 0; i < input.length; i++)
            recipes[i] = addRecipe(tag, aspects, output, input[i]);
        return recipes;
    }
}
