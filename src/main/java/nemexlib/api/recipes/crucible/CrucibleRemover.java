package nemexlib.api.recipes.crucible;

import nemexlib.api.recipes.ARecipeRemover;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRemover extends ARecipeRemover {

    protected static final CrucibleRemover instance = new CrucibleRemover();

    protected CrucibleRemover() {}

    public static CrucibleRemover i() {
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
        for (Object recipe : recipes) {
            CrucibleRecipe r = (CrucibleRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem());
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException | ClassCastException ignored) {}
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
        for (Object recipe : recipes) {
            CrucibleRecipe r = (CrucibleRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize;
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException | ClassCastException ignored) {}
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
        for (Object recipe : recipes) {
            CrucibleRecipe r = (CrucibleRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput() != null
                        && r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException | ClassCastException ignored) {}
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
        for (Object recipe : recipes) {
            CrucibleRecipe r = (CrucibleRecipe) recipe;
            try {
                boolean condition = r.getRecipeOutput() != null
                        && r.getRecipeOutput().getItem().equals(output.getItem())
                        && r.getRecipeOutput().stackSize == output.stackSize
                        && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                if (condition) this.recipesToRemove.add(r);
            } catch (NullPointerException | ClassCastException ignored) {}
        }
        removeFoundRecipes();
    }

    /**
     * Refresh the collection with the Thaumcraft 4 recipes
     */
    @Override
    public void refresh() {
        recipes = ThaumcraftApi.getCraftingRecipes();
    }
}
