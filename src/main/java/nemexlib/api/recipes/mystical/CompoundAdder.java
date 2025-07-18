package nemexlib.api.recipes.mystical;

import nemexlib.api.thaumcraft.aspects.Aspects;
import nemexlib.api.util.exceptions.CompoundRecipeSizeIsDifferentFromStructure;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import thaumcraft.common.config.ConfigResearch;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public class CompoundAdder {

    /**
     * Create and register a compound recipe to the TC4 registry
     * @param tag The recipe tag
     * @param aspects The vis needed to perform the recipe
     * @param width The max width for blocks
     * @param height The max height for blocks
     * @param length The max length for blocks
     * @param structure All the blocks for the recipe structure (all must be on the same array dimension)
     * @return The recipe generated
     */
    public static List addCompoundRecipe(String tag, Aspects aspects, int width, int height, int length, Object ... structure) {
        if (tag == null || structure == null || structure.length == 0) throw new ParameterIsNullOrEmpty();
        if (width * height * length != structure.length) throw new CompoundRecipeSizeIsDifferentFromStructure(width * height * length, structure.length);
        return registerRecipe(tag, Arrays.asList(aspects, width, height, length, Arrays.asList(structure)));
    }

    /**
     * Allow for registering a compound recipe to the TC4 registry
     * @param key The recipe tag
     * @param recipe The full compound recipe
     * @return The parameter recipe
     */
    protected static List registerRecipe(String key, List recipe) {
        ConfigResearch.recipes.put(key, recipe);
        return recipe;
    }
}
