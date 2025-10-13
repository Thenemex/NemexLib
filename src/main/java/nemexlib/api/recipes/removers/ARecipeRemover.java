package nemexlib.api.recipes.removers;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes, unused")
public abstract class ARecipeRemover implements IRecipeRemover {

    protected final ArrayList<Object> recipesToRemove = new ArrayList<>();
    protected List recipes;

    protected ARecipeRemover() {}

    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item</p>
     */
    public abstract void removeItem(ItemStack output);
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item and Amount</p>
     */
    public abstract void removeAmount(ItemStack output);
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item and metadata.</p>
     */
    public abstract void removeMeta(ItemStack output);
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item, metadata, amount.</p>
     */
    public abstract void removePrecise(ItemStack output);

    /**
     * Refresh the collection with the recipe registry chosen
     */
    public abstract void refresh();

    /**
     * Returns the last recipe added to the collection of recipes chosen.
     * @return The last object of the recipes list
     */
    public IRecipe getLastRecipeAdded() {
        this.refresh();
        return (IRecipe) recipes.get(recipes.size() - 1);
    }

    /**
     * Remove all recipes found by the other methods from the recipes list from the chosen recipe collection
     */
    protected void removeFoundRecipes() {
        for (Object removedRecipe : recipesToRemove)
            this.recipes.remove(removedRecipe);
        this.recipesToRemove.clear();
    }
}
