package nemexlib.api.recipes.finders;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IRecipeFinder<TRecipe> {

    TRecipe findRecipeItem(ItemStack output);
    TRecipe findRecipeAmount(ItemStack output);
    TRecipe findRecipeMeta(ItemStack output);
    TRecipe findRecipePrecise(ItemStack output);

    TRecipe[] findRecipesItem(ItemStack output);
    TRecipe[] findRecipesAmount(ItemStack output);
    TRecipe[] findRecipesMeta(ItemStack output);
    TRecipe[] findRecipesPrecise(ItemStack output);
}
