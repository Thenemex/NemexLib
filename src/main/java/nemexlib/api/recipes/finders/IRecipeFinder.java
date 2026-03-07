package nemexlib.api.recipes.finders;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IRecipeFinder<TRecipe> {

    void checkParameters(ItemStack item);

    TRecipe findRecipeItem(ItemStack item, boolean isOutput);
    TRecipe findRecipeAmount(ItemStack item, boolean isOutput);
    TRecipe findRecipeMeta(ItemStack item, boolean isOutput);
    TRecipe findRecipePrecise(ItemStack item, boolean isOutput);

    ArrayList<TRecipe> findRecipesItem(ItemStack item, boolean isOutput);
    ArrayList<TRecipe> findRecipesAmount(ItemStack item, boolean isOutput);
    ArrayList<TRecipe> findRecipesMeta(ItemStack item, boolean isOutput);
    ArrayList<TRecipe> findRecipesPrecise(ItemStack item, boolean isOutput);
}
