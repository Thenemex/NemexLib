package nemexlib.api.recipes;

import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class RecipeFinder {

    /**
     * Return the first recipe matching the output item given
     * <p>This method checks for : Item</p>
     * @return If a matching recipe is found, returns the recipe. If not it will return null.
     */
    public static IRecipe findRecipeItem(ItemStack output) {
        if (output == null) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        boolean condition;
        for(Object recipe : CraftingManager.getInstance().getRecipeList()) {
            r = (IRecipe) recipe;
            try {
                condition = r.getRecipeOutput().getItem().equals(output.getItem());
                if (condition) return r;
            } catch (NullPointerException ignored) {}
        }
        return null;
    }
    /**
     * Return the first recipe matching the output item given
     * <p>This method checks for : Item and Amount</p>
     * @return If a matching recipe is found, returns the recipe. If not it will return null.
     */
    public static IRecipe findRecipeAmount(ItemStack output) {
        if (output == null) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        boolean condition;
        for(Object recipe : CraftingManager.getInstance().getRecipeList()) {
            r = (IRecipe) recipe;
            try {
                condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize;
                if (condition) return r;
            } catch (NullPointerException ignored) {}
        }
        return null;
    }
    /**
     * Return the first recipe matching the output item given
     * <p>This method checks for : Item and metadata</p>
     * @return If a matching recipe is found, returns the recipe. If not it will return null.
     */
    public static IRecipe findRecipeMeta(ItemStack output) {
        if (output == null) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        boolean condition;
        for(Object recipe : CraftingManager.getInstance().getRecipeList()) {
            r = (IRecipe) recipe;
            try {
                condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) return r;
            } catch (NullPointerException ignored) {}
        }
        return null;
    }
    /**
     * Return the first recipe matching the output item given
     * <p>This method checks for : Item, metadata, amount</p>
     * @return If a matching recipe is found, returns the recipe. If not it will return null.
     */
    public static IRecipe findRecipePrecise(ItemStack output) {
        if (output == null) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        boolean condition;
        for(Object recipe : CraftingManager.getInstance().getRecipeList()) {
            r = (IRecipe) recipe;
            try {
                condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) return r;
            } catch (NullPointerException ignored) {}
        }
        return null;
    }
}
