package nemexlib.api.recipes.finders;

import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.util.exceptions.RecipeCollectionIsNotSetOrEmpty;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"UnusedReturnValue", "rawtypes"})
public abstract class ACollectionRecipeFinder<TRecipe> implements IRecipeFinder<TRecipe> {

    protected final ArrayList<TRecipe> recipesFound;
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
}
