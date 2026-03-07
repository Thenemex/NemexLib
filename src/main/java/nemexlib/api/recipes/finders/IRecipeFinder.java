package nemexlib.api.recipes.finders;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IRecipeFinder<TRecipe> {

    TRecipe findRecipeItem(ItemStack output);
    TRecipe findRecipeAmount(ItemStack output);
    TRecipe findRecipeMeta(ItemStack output);
    TRecipe findRecipePrecise(ItemStack output);

    ArrayList<TRecipe> findRecipesItem(ItemStack output);
    ArrayList<TRecipe> findRecipesAmount(ItemStack output);
    ArrayList<TRecipe> findRecipesMeta(ItemStack output);
    ArrayList<TRecipe> findRecipesPrecise(ItemStack output);
}
