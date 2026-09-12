package nemexlib.api.recipes.finders;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

@SuppressWarnings("UnusedReturnValue")
public interface IRecipeFinder<TRecipe> {

    TRecipe findRecipeItem(final ItemStack output);
    TRecipe findRecipeAmount(final ItemStack output);
    TRecipe findRecipeMeta(final ItemStack output);
    TRecipe findRecipePrecise(final ItemStack output);

    ArrayList<TRecipe> findRecipesItem(final ItemStack output);
    ArrayList<TRecipe> findRecipesAmount(final ItemStack output);
    ArrayList<TRecipe> findRecipesMeta(final ItemStack output);
    ArrayList<TRecipe> findRecipesPrecise(final ItemStack output);
}
