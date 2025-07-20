package nemexlib.api.recipes.workbench;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import nemexlib.api.recipes.ARecipeRemover;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import java.util.List;

@SuppressWarnings({"unused", "rawtypes", "DataFlowIssue"})
public class WorkbenchRemover extends ARecipeRemover {

    protected static final WorkbenchRemover instance = new WorkbenchRemover();

    protected WorkbenchRemover() {}

    public static WorkbenchRemover i() {
        return instance;
    }

    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item</p>
     */
    @Override
    public void removeItem(ItemStack output) {
        this.refresh();
        if (recipes == null || output == null || recipes.isEmpty()) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        for (Object recipe : recipes) {
            r = (IRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem());
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException ignored) {}
        }
        removeFoundRecipes();
    }
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item and Amount</p>
     */
    @Override
    public void removeAmount(ItemStack output) {
        this.refresh();
        if (recipes == null || output == null || recipes.isEmpty()) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        for (Object recipe : recipes) {
            r = (IRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize;
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException ignored) {}
        }
        removeFoundRecipes();
    }
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item and metadata.</p>
     */
    @Override
    public void removeMeta(ItemStack output) {
        this.refresh();
        if (recipes == null || output == null || recipes.isEmpty()) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        for (Object recipe : recipes) {
            r = (IRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException ignored) {}
        }
        removeFoundRecipes();
    }
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item, metadata, amount.</p>
     */
    @Override
    public void removePrecise(ItemStack output) {
        this.refresh();
        if (recipes == null || output == null || recipes.isEmpty()) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        for (Object recipe : recipes) {
            r = (IRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException ignored) {}
        }
        removeFoundRecipes();
    }

    /**
     * Remove all recipes with set output and input
     * <p>This method checks for output : Item, Metadata, Amount</p>
     * <p>This method checks for input : Item, Metadata</p>
     * @param output The output ItemStack
     * @param isShapeless If the seek recipe is shapeless or not
     * @param input The input item to be checked on the recipe
     */
    public void removeItemFromInput(ItemStack output, boolean isShapeless, ItemStack input) {
        this.refresh();
        if (recipes == null || output == null || input == null) throw new ParameterIsNullOrEmpty();
        IRecipe r;
        for (Object recipe : recipes) {
            r = isShapeless ? (ShapelessRecipes) recipe : (ShapedRecipes) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) {
                    boolean condition2 = false;
                    for (Object o : (List) (isShapeless ? ((ShapelessRecipes) r).recipeItems : ((ShapedRecipes) r).recipeItems)) {
                        ItemStack item = (ItemStack) o;
                        condition2 = item.getItem().equals(input.getItem())
                                && item.getItemDamage() == input.getItemDamage();
                    }
                    if (condition2) this.recipesToRemove.add(r);
                }
            } catch (NullPointerException | ClassCastException ignored) {}
        }
        removeFoundRecipes();
    }


    /**
     * Refresh the collection with the vanilla recipes
     */
    @Override
    public void refresh() {
        recipes = CraftingManager.getInstance().getRecipeList();
    }
}
