package nemexlib.api.recipes.workbench;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import nemexlib.api.items.DeepCopy;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.util.exceptions.ParameterArraysSizeException;

import java.util.ArrayList;

public class WorkbenchAdder {
// Example for Object : new Object[]{"CRC","RRR","CRC", 'C', Blocks.coal_block, 'R', Items.redstone});

    private WorkbenchAdder(){}

    /**
     * Adds a single recipe from multiple items
     * @param output The output ItemStack
     * @param isShapeless if the recipe is shapeless or not
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addRecipe(ItemStack output, boolean isShapeless, Object ... recipe) {
        if (output == null || recipe == null || recipe.length == 0) throw new ParameterIsNullOrEmpty();
        if (isShapeless) GameRegistry.addShapelessRecipe(output, recipe);
        else GameRegistry.addShapedRecipe(output, recipe);
        return WorkbenchRemover.i().getLastRecipeAdded();
    }

    /**
     * Adds multiple recipes that contain multiple items into the same output
     * <p>The method will set the recipe as for exemple : input[0] -> output, input[1] -> output, etc ...</p>
     * @param output The output items
     * @param isShapeless if the recipe is shapeless or not
     * @param input The inputs items & recipe
     * @return The recipes generated
     */
    public static IRecipe[] addMultipleSingleRecipes(ItemStack output, boolean isShapeless, ArrayList<Object[]> input) {
        IRecipe[] recipes = new IRecipe[input.size()];
        for (int i = 0; i < recipes.length; i++)
            recipes[i] = addRecipe(output, isShapeless, input.get(i));
        return recipes;
    }

    /**
     * Adds recipes that contain only one item into another.
     * <p>The method will set the recipe as for exemple : input[0] -> output[0], input[1] -> output[1], etc ...</p>
     * @param output The output items
     * @param input The input items
     * @return The recipes generated
     */
    public static IRecipe[] addMultipleSingleShapelessRecipes(ItemStack[] output, ItemStack[] input) {
        if (output == null || input == null) throw new ParameterIsNullOrEmpty();
        if (output.length != input.length) throw new ParameterArraysSizeException(output.length, input.length);
        IRecipe[] recipes = new IRecipe[output.length];
        for (int i = 0; i < output.length; i++)
            recipes[i] = addRecipe(output[i], true, input[i]);
        return recipes;
    }

    /**
     * Adds recipes that contain one item into another, with possibility to edit the amount of output items
     * <p>The method will set the recipe as for exemple : input[0] -> output[0], input[1] -> output[1], etc ...</p>
     * @param output The output items
     * @param nb The number of output items wanted
     * @param input The input items
     * @return The recipes generated
     */
    public static IRecipe[] addMultipleSingleShapelessRecipes(ItemStack[] output, int nb, ItemStack[] input) {
        return addMultipleSingleShapelessRecipes(DeepCopy.i(output, nb), input);
    }
}
