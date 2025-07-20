package nemexlib.api.recipes.workbench;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import nemexlib.api.recipes.ARecipeRemover;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;

@SuppressWarnings({"unused"})
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
     * Refresh the collection with the vanilla recipes
     */
    @Override
    public void refresh() {
        recipes = CraftingManager.getInstance().getRecipeList();
    }
}
