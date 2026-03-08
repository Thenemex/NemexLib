package nemexlib.api.recipes.finders;

import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.util.exceptions.RecipeCollectionIsNotSetOrEmpty;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"UnusedReturnValue", "rawtypes", "unchecked"})
public abstract class ACollectionRecipeFinder<TRecipe> implements IRecipeFinder<TRecipe> {

    private final ArrayList<TRecipe> recipesFound;
    protected Collection[] registries;

    public ACollectionRecipeFinder(Collection ... registries) {
        this(5, registries);
    }
    public ACollectionRecipeFinder(int amount, Collection ... registries) {
        this.recipesFound = new ArrayList<>(amount);
        this.setRegistries(registries);
    }

    public ACollectionRecipeFinder<TRecipe> setRegistries(Collection ... registries) {
        if (registries == null || registries.length == 0) throw new ParameterIsNullOrEmpty();
        this.registries = registries;
        return this;
    }

    /**
     * Check the inner parameters of the instance.
     * @throws RecipeCollectionIsNotSetOrEmpty Will throw exception if the instance is missing registries to iterate on to find recipes
     * @throws ParameterIsNullOrEmpty Will throw exception if the parameter is null
     */
    public void checkParameters(ItemStack item) {
        if (registries == null || registries.length == 0) throw new RecipeCollectionIsNotSetOrEmpty();
        if (item == null) throw new ParameterIsNullOrEmpty();
    }


    /**
     * Add a recipe to the list of found recipes
     */
    public void addFoundRecipe(TRecipe recipe) {
        this.recipesFound.add(recipe);
    }
    /**
     * Clears the list of recipes found
     */
    public void clearFoundRecipes() {
        this.recipesFound.clear();
    }

    /**
     * Returns true if the list of found recipes is empty
     */
    public boolean isFoundRecipesListEmpty() {
        return recipesFound.isEmpty();
    }
    /**
     * Returns the amount of recipes present in the list of found recipes
     */
    public int getAmountOfFoundRecipes() {
        return recipesFound.size();
    }

    /**
     * Returns a copy of the list of found recipes
     */
    public ArrayList<TRecipe> getFoundRecipes() {
        return new ArrayList<>(recipesFound);
    }
}
