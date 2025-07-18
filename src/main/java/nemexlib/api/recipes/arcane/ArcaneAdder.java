package nemexlib.api.recipes.arcane;

import net.minecraft.item.ItemStack;
import nemexlib.api.items.DeepCopy;
import nemexlib.api.util.exceptions.ParameterArraysSizeException;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.thaumcraft.aspects.Aspects;
import nemexlib.api.util.exceptions.ParameterValueIsNegativeOrZero;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

import java.util.ArrayList;

@SuppressWarnings("unchecked, unused")
public class ArcaneAdder {

    protected static ShapedArcaneRecipe registerShapedArcanePRIORITY(String research, ItemStack result, AspectList aspects, Object... recipe) {
        ShapedArcaneRecipe r = new ShapedArcaneRecipe(research, result, aspects, recipe);
        ThaumcraftApi.getCraftingRecipes().add(0, r);
        return r;
    }
    protected static ShapelessArcaneRecipe registerShapelessArcanePRIORITY(String research, ItemStack result, AspectList aspects, Object... recipe) {
        ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
        ThaumcraftApi.getCraftingRecipes().add(r);
        return r;
    }
    protected static ShapedArcaneRecipe registerShapedArcane(String research, ItemStack result, AspectList aspects, Object... recipe) {
        return ThaumcraftApi.addArcaneCraftingRecipe(research, result, aspects, recipe);
    }
    protected static ShapelessArcaneRecipe registerShapelessArcane(String research, ItemStack result, AspectList aspects, Object... recipe) {
        return ThaumcraftApi.addShapelessArcaneCraftingRecipe(research, result, aspects, recipe);
    }


    /**
     * Adds a single arcane recipe from multiple items
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param output The output ItemStack
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IArcaneRecipe addArcane(String tag, Aspects aspects, boolean isShapeless, boolean priority, ItemStack output, Object ... recipe) {
        if (tag == null || aspects == null || output == null || recipe == null || recipe.length == 0) throw new ParameterIsNullOrEmpty();
        if (isShapeless) return registerShapelessArcane(tag, output, aspects, recipe);
        else return registerShapedArcane(tag, output, aspects, recipe);
    }

    /**
     * Adds multiple recipes
     * <p>Will follow this pattern : input[0] -> output[0],  input[1] -> output[1] ...</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param output The output items
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleArcane(String tag, Aspects aspects, boolean isShapeless, boolean priority, ItemStack[] output, ArrayList<Object[]> input) {
        if (output.length != input.size()) throw new ParameterArraysSizeException(output.length, input.size());
        IArcaneRecipe[] recipes = new IArcaneRecipe[output.length];
        for (int i = 0; i < output.length; i++)
            recipes[i] = addArcane(tag, aspects, isShapeless, priority, output[i], input.get(i));
        return recipes;
    }

    /**
     * Adds multiple recipes, with possibility to edit the amount of output
     * <p>Will follow this pattern : input[0] -> output[0],  input[1] -> output[1] ...</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param output The output items
     * @param nb The number of output items wanted
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleArcane(String tag, Aspects aspects, ItemStack[] output, int nb, ArrayList<Object[]> input) {
        if (nb < 1) throw new ParameterValueIsNegativeOrZero(nb);
        return addMultipleArcane(tag, aspects, false, false, DeepCopy.i(output, nb), input);
    }

    /**
     * Adds multiple recipes, with possibility to edit the amount of output ; and to make it shapeless
     * <p>Will follow this pattern : input[0] -> output[0],  input[1] -> output[1] ...</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param isShapeless If true, the recipe will be shapeless
     * @param output The output items
     * @param nb The number of output items wanted
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleArcane(String tag, Aspects aspects, boolean isShapeless, ItemStack[] output, int nb, ArrayList<Object[]> input) {
        return addMultipleArcane(tag, aspects, isShapeless, false, DeepCopy.i(output, nb), input);
    }

    /**
     * Adds multiple recipes, with possibility to edit the amount of output ; and to make it shapeless
     * <p>Will follow this pattern : input[0] -> output[0],  input[1] -> output[1] ...</p>
     * <p>If priority parameter is set to true, the recipes will be placed at the start of the TC4 Registry</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param isShapeless If true, the recipe will be shapeless
     * @param priority If true, will be placed first in TC4 recipe register
     * @param output The output items
     * @param nb The number of output items wanted
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleArcane(String tag, Aspects aspects, boolean isShapeless, boolean priority, ItemStack[] output, int nb, ArrayList<Object[]> input) {
        return addMultipleArcane(tag, aspects, isShapeless, priority, DeepCopy.i(output, nb), input);
    }

    /**
     * Adds recipes that contain items into another unique item (with same aspects for every recipe) ; with shapeless option
     * <p>The method will set the recipe as for exemple : input[0] -> output, input[1] -> output ...</p>
     * <p>If priority parameter is set to true, the recipes will be placed at the start of the TC4 Registry</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param isShapeless If true, the recipe will be shapeless
     * @param priority If true, will be placed first in TC4 recipe register
     * @param output The single output
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleSingleArcane(String tag, Aspects aspects, boolean isShapeless, boolean priority, ItemStack output, ArrayList<Object[]> input) {
        if (input == null) throw new ParameterIsNullOrEmpty();
        if (input.isEmpty()) throw new ParameterIsNullOrEmpty();
        IArcaneRecipe[] recipes = new IArcaneRecipe[input.size()];
        for (int i = 0; i < input.size(); i++)
            recipes[i] = addArcane(tag, aspects, isShapeless, priority, output, input.get(i));
        return recipes;
    }

    /**
     * Adds recipes that contain items into another unique item (with same aspects for every recipe).
     * <p>The method will set the recipe as for exemple : input[0] -> output, input[1] -> output ...</p>
     * <p>If priority parameter is set to true, the recipes will be placed at the start of the TC4 Registry</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param priority If true, will be placed first in TC4 recipe register
     * @param output The single output
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleSingleArcane(String tag, Aspects aspects, boolean priority, ItemStack output, ArrayList<Object[]> input) {
        return addMultipleSingleArcane(tag, aspects, false, priority, output, input);
    }

    /**
     * Adds recipes that contain items into another unique item (with same aspects for every recipe).
     * <p>The method will set the recipe as for exemple : input[0] -> output, input[1] -> output ...</p>
     * @param tag Research's tag
     * @param aspects Wand's vis used
     * @param output The single output
     * @param input The recipe structures
     * @return The recipes generated
     */
    public static IArcaneRecipe[] addMultipleSingleArcane(String tag, Aspects aspects, ItemStack output, ArrayList<Object[]> input) {
        return addMultipleSingleArcane(tag, aspects, false, false, output, input);
    }


}
