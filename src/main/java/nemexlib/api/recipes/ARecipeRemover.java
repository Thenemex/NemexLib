package nemexlib.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes, unused")
public abstract class ARecipeRemover {

    protected final ArrayList<Object> recipesToRemove = new ArrayList<>();
    protected List recipes;

    protected ARecipeRemover() {}

    public abstract void removeItem(ItemStack output);
    public abstract void removeAmount(ItemStack output);
    public abstract void removeMeta(ItemStack output);
    public abstract void removePrecise(ItemStack output);

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
