package nemexlib.api.recipes.workbench;

import cpw.mods.fml.common.registry.GameRegistry;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class OreDictAdder {

    private OreDictAdder() {}

    /**
     * Adds a single OreDict recipe from the object recipe
     * @param output The output ItemStack
     * @param isShapeless If the recipe is shapeless or not
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addRecipe(ItemStack output, boolean isShapeless, Object ... recipe) {
        if (output == null || recipe == null || recipe.length == 0) throw new ParameterIsNullOrEmpty();
        IRecipe oreRecipe;
        if (isShapeless) oreRecipe = new ShapelessOreRecipe(output, recipe);
        else oreRecipe = new ShapedOreRecipe(output, recipe);
        GameRegistry.addRecipe(oreRecipe);
        return oreRecipe;
    }
    /**
     * Adds a single OreDict recipe from the object recipe
     * <p>The stackSize will be set to 1. and metadata to 0</p>
     * @param output The output Item
     * @param isShapeless If the recipe is shapeless or not
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addRecipe(Item output, boolean isShapeless, Object ... recipe) {
        return addRecipe(new ItemStack(output), isShapeless, recipe);
    }
    /**
     * Adds a single OreDict recipe from the object recipe
     * <p>The stackSize will be set to 1. and metadata to 0</p>
     * @param output The output Block
     * @param isShapeless If the recipe is shapeless or not
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addRecipe(Block output, boolean isShapeless, Object ... recipe) {
        return addRecipe(new ItemStack(output), isShapeless, recipe);
    }

    /**
     * Adds a single OreDict Shaped recipe from the object recipe
     * @param output The output ItemStack
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addShapedRecipe(ItemStack output, Object ... recipe) {
        return addRecipe(output, false, recipe);
    }
    /**
     * Adds a single OreDict Shaped recipe from the object recipe
     * <p>The stackSize will be set to 1. and metadata to 0</p>
     * @param output The output Item
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addShapedRecipe(Item output, Object ... recipe) {
        return addRecipe(output, false, recipe);
    }
    /**
     * Adds a single OreDict Shaped recipe from the object recipe
     * <p>The stackSize will be set to 1. and metadata to 0</p>
     * @param output The output Block
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addShapedRecipe(Block output, Object ... recipe) {
        return addRecipe(output, false, recipe);
    }

    /**
     * Adds a single OreDict Shaped recipe from the object recipe
     * @param output The output ItemStack
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addShapelessRecipe(ItemStack output, Object ... recipe) {
        return addRecipe(output, true, recipe);
    }
    /**
     * Adds a single OreDict Shaped recipe from the object recipe
     * <p>The stackSize will be set to 1. and metadata to 0</p>
     * @param output The output Item
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addShapelessRecipe(Item output, Object ... recipe) {
        return addRecipe(output, true, recipe);
    }
    /**
     * Adds a single OreDict Shaped recipe from the object recipe
     * <p>The stackSize will be set to 1. and metadata to 0</p>
     * @param output The output Block
     * @param recipe The whole recipe object structure
     * @return The recipe generated
     */
    public static IRecipe addShapelessRecipe(Block output, Object ... recipe) {
        return addRecipe(output, true, recipe);
    }
}
